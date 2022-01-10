package billydev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillydevAutoConfiguration {
    @Bean
    public PerformanceInformationCollectBean getPerfomranceBean(){
        return new PerformanceInformationCollectBean();
    }
}
