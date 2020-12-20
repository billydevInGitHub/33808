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
            /*
            create new Employee
             */
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


            /*
            get all employee information
             */
            System.out.println("first print:"+webClient.get()
                    .uri("http://localhost:8080")
                    .retrieve()
                    .bodyToFlux(Employee.class)
                    .collectList().block());

            /*
            do the update of employee
             */
            employee = new Employee();
            employee.setId(19);
            employee.setName("updated Name of 18");
            employee.setSalary(19000);
            webClient.put().uri("http://localhost:8080//update")
                    .body(Mono.just(employee), Employee.class)
                    .exchange().subscribe();


                        /*
            get all employee information
             */
            System.out.println("second print:"+webClient.get()
                    .uri("http://localhost:8080")
                    .retrieve()
                    .bodyToFlux(Employee.class)
                    .collectList().block());

            System.out.println("Command line run completed");
        };
    }
}