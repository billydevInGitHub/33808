package hello;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxApplication.class, args);
	}
	@Bean
	public Counter createCounter(MeterRegistry meterRegistry) {
		return Counter.builder("greeting.count")
				.description("count the greeting controller requests")
				.register(meterRegistry);
	}
}
