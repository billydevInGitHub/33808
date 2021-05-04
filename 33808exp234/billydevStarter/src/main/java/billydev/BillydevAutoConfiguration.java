package billydev;


import billydev.service.bean.ServiceBean;
import com.sun.tracing.ProbeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@EnableConfigurationProperties(OneClickProperties.class)
public class BillydevAutoConfiguration {

    @Autowired
    ServiceBean serviceBean;


    @Bean
    public BillydevBean getBillydevBean(){
        return new BillydevBean();
    }

    @Bean
    public ServiceBean getTcpDumpBean(){
        return new ServiceBean();
    }


    @GetMapping("/oneclick")
    public String getOneClickInfo(){
        return serviceBean.service(6);

    }


}
