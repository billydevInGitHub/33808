package com.asimio.demo.rest;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.asimio.demo.Application;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

/**
 * @author Orlando L Otero - https://tech.asimio.net
 * {@see FilmDaoTest}
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:schema.sql", "classpath:data.sql" }),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:purge.sql")
})
public class FilmControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = this.port;
    }

    @Test
    public void shouldRetrieveFilmsWhen_MinAndMaxRateInZero99AndOne99AndReleasedIn2006AndCategoryIsActionOrComedy() {
        JsonPath jsonPath = RestAssured
                .given()
                    .accept(ContentType.JSON)
                    .queryParam("minRentalRate", "0.99")
                    .queryParam("maxRentalRate", "1.99")
                    .queryParam("releaseYear", "2006")
                    .queryParam("category", "Action")
                    .queryParam("category", "Comedy")
                .when()
                    .get("/api/films")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .contentType(ContentType.JSON)
                    .extract()
                    .jsonPath();

        List<Map<String, Object>> actualFilms = jsonPath.get("$");
        Assert.assertThat(actualFilms.size(), Matchers.equalTo(44));
        // More assertions
    }
}