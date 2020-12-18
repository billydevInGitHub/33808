package billydev.test;


import billydev.entity.Student;
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
import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void getStudentById() throws Exception {

        String expected = "{\"id\":2,\"name\":\"student2\",\"clazzId\":1,\"number\":\"s2\",\"age\":12}";

        ResponseEntity<String> response = restTemplate
                .getForEntity("/student/2", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), true);

    }

    @Test
    public void insertStudentsWithViolation() throws Exception {
        Student student1 = new Student();
        student1.setClazzId(1);
        student1.setName("batch insert student1");
        student1.setAge(12);
        student1.setNumber("student num1 for batch insert");

        Student student2 = new Student();
        student2.setClazzId(1);
        student2.setName("batch insert student2");
        student2.setAge(30);
        student2.setNumber("student num2 for batch insert");

        List<Student> studentList=new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        ResponseEntity<String> response = restTemplate.postForEntity("/students", studentList, String.class);
        printJSON(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void insertStudents() throws Exception {
        Student student3 = new Student();
        student3.setClazzId(1);
        student3.setName("batch insert student3");
        student3.setAge(12);
        student3.setNumber("student num3 for batch insert");

        Student student4 = new Student();
        student4.setClazzId(1);
        student4.setName("batch insert student4");
        student4.setAge(20);
        student4.setNumber("student num4 for batch insert");

        List<Student> studentList=new ArrayList<>();
        studentList.add(student3);
        studentList.add(student4);
        ResponseEntity<String> response = restTemplate.postForEntity("/students", studentList, String.class);
        printJSON(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void updateStudent() throws Exception {
        Student student = new Student();
        student.setId(1);
        student.setName("updated student");
        student.setClazzId(1);
        student.setAge(10);
        student.setNumber("updated student number");
        restTemplate.put("/student/1", student);
        String expected= "{\"id\":1,\"name\":\"updated student\",\"clazzId\":1,\"number\":\"updated student number\",\"age\":10}";

        ResponseEntity<String> response = restTemplate
                .getForEntity("/student/1", String.class);

        printJSON(response);

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

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
