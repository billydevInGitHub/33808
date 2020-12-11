package billydev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author Billy
 */
@Configuration
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    protected UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        if (!manager.userExists("billy")) {
            manager.createUser(User.withUsername("billy").password(passwordEncoder().encode("123456")).roles("admin").build());
        }
        if (!manager.userExists("user")) {
            manager.createUser(User.withUsername("user").password(passwordEncoder().encode("123456")).roles("user").build());
        }
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
