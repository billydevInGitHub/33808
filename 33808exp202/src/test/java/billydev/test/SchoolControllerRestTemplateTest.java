package billydev.test;


import billydev.mapper.ClazzMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("Test")
public class SchoolControllerRestTemplateTest {

    private static final ObjectMapper om = new ObjectMapper();

    //@WithMockUser is not working with TestRestTemplate
    @Autowired
    private TestRestTemplate restTemplate;



    @Autowired
    ClazzMapper clazzMapper;

    @Before
    public void init() {
//        Book book = new Book(1L, "A Guide to the Bodhisattva Way of Life", "Santideva", new BigDecimal("15.41"));
//        when(mockRepository.findById(1L)).thenReturn(Optional.of(book));
    }

    @WithMockUser(username = "billy", password = "123456", roles = "admin")
    @Test
    public void getStudentById() throws Exception {

        String expected = "{\"id\":1,\"name\":\"student1\",\"clazzId\":1,\"number\":\"s1\",\"age\":10}";

        ResponseEntity<String> response = restTemplate
                .getForEntity("/student/1", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), true);

    }

    @Test
    public void find_nologin_401() throws Exception {

        String expected = "{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Unauthorized\",\"path\":\"/books/1\"}";

        ResponseEntity<String> response = restTemplate
                .getForEntity("/books/1", String.class);

        printJSON(response);

//        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
//        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
//        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
            System.out.println("pring json myself:"+object.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
