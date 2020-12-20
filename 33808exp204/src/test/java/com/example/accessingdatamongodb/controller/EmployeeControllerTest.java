package com.example.accessingdatamongodb.controller;

import com.example.accessingdatamongodb.model.Employee;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class EmployeeControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void create() {

    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
        Employee employee=new Employee();
        employee.setId(20);
        employee.setName("for Unit test");
        employee.setSalary(20);
        webTestClient.post().uri("/create").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(employee), Employee.class)
                .exchange().expectStatus().isCreated();
        webTestClient.delete().uri("/delete/20").exchange().expectStatus().isOk();
    }
}