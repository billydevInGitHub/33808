package billydev;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigA {
    @Bean
    public SimpleBeanA simpleBeanA() {
        return new SimpleBeanA();
    }

    @Bean
    public SimpleBeanAConsumer simpleBeanAConsumer() {
        return new SimpleBeanAConsumer(simpleBeanA());
    }

}
