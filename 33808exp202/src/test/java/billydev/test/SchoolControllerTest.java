package billydev.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
class SchoolControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllStudentsByClassId() throws Exception {
        mockMvc
                .perform(get("/class/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void insertStudent() throws Exception {
        MockHttpServletRequestBuilder request = post("/student");
        request.content("{\"clazzId\":1,\"name\":\"name from test\",\"age\":10,\"number\":\"123456\"}");
        request.locale(Locale.ENGLISH);
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isCreated()).andDo(print()).andReturn();
        assertEquals(result.getResponse().getContentAsString(),"1");
    }

    @Test
    void insertStudents() throws Exception {
        MockHttpServletRequestBuilder request = post("/students");
        request.content("[{\"clazzId\":1,\"name\":\"name from test1\",\"age\":10,\"number\":\"123456\"},{\"clazzId\":1,\"name\":\"name from test2\",\"age\":30,\"number\":\"123456\"}]");
        request.locale(Locale.ENGLISH);
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isCreated()).andDo(print()).andReturn();
        assertEquals(result.getResponse().getContentAsString(),"2");
    }

    @Test
    void getStudentById() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }
}