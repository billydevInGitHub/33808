package com.devglan.actuatordemo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class ActuatorDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActuatorDemoApplication.class, args);
		
	}

	@GetMapping("/greeting")
	public String greet(){
		return "Hello there!";
	}

	private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

	@Bean
	ApplicationRunner applicationRunner(MeterRegistry meterRegistry){
		return args-> {
				scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
					@Override
					public void run() {
						meterRegistry.timer("tranform-phone-task")
								.record(Duration.ofMillis(new Random().nextInt()));
					}
				},500,500, TimeUnit.MILLISECONDS);
			};
		}
}
