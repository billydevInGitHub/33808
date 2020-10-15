package com.devglan.actuatordemo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ActuatorDemoApplication {

    @Autowired
    Counter counter;

    public static void main(String[] args) {
        SpringApplication.run(ActuatorDemoApplication.class, args);

    }

    @GetMapping("/greeting")
    public String greet() {
        counter.increment();
        return "Hello there!";
    }

    @Bean
    public Counter createCounter(MeterRegistry meterRegistry) {
        return Counter.builder("greeting.count")
                .description("count the greeting controller requests")
                .register(meterRegistry);
    }

}
