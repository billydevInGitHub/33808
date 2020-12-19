package com.example.accessingdatamongodb;

import com.example.accessingdatamongodb.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class WebfluxFunctionalApp {

//    @Autowired
//    WebClient webClient=WebClient.builder().build();

    public static void main(String[] args) {
        SpringApplication.run(WebfluxFunctionalApp.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Employee employee = new Employee();
            employee.setId(18);
            employee.setName("testName18");
            employee.setSalary(1800);

            WebClient webClient=WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
            webClient.post()
                    .uri("http://localhost:8080//create")
                    .body(Mono.just(employee), Employee.class).retrieve().bodyToMono(Object.class).subscribe();
            System.out.println("Command line run");


            System.out.println(webClient.get()
                    .uri("http://localhost:8080")
                    .retrieve()
                    .bodyToFlux(Employee.class)
                    .collectList().block());
        };
    }
}