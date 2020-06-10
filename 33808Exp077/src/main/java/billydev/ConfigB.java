package billydev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ConfigB {
    @Bean
    public SimpleBeanB simpleBeanB() {
        return new SimpleBeanB();
    }

    @Bean
    public SimpleBeanBConsumer simpleBeanBConsumer() {
        return new SimpleBeanBConsumer(simpleBeanB());
    }

}
