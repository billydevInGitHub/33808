package billydev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigC {
    @Autowired
    SimpleBeanC simpleBeanC;

    @Bean
    public SimpleBeanC simpleBeanC() {
        return new SimpleBeanC();
    }

    @Bean
    public SimpleBeanCConsumer simpleBeanCConsumer() {
        return new SimpleBeanCConsumer(simpleBeanC);
    }

}
