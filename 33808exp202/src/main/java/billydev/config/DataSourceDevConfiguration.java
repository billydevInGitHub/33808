package billydev.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * @author Billy
 */
@Configuration
@MapperScan(value = { "billydev.mapper" }, sqlSessionFactoryRef = "sqlSessionFactory")
@Profile("Dev")
public class DataSourceDevConfiguration {

    @Bean(name = "dataSourceDev")
    @ConfigurationProperties(prefix = "spring.datasource.mem")
    public DataSource dataSource() {
        return new BasicDataSource();
    }

    @Bean(name = "transactionManagerDev")
    public DataSourceTransactionManager dbOneTransactionManager(
            @Qualifier("dataSourceDev") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactoryDev")
    @ConditionalOnMissingBean(name = "sqlSessionFactoryDev")
    public SqlSessionFactory dbOneSqlSessionFactory(@Qualifier("dataSourceDev") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:sqlMapper/*Mapper.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer()
    {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("db-setup.sql"));
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(true);
        return dataSourceInitializer;
    }
}
