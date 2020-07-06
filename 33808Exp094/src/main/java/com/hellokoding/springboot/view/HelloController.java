package com.hellokoding.springboot.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellokoding.springboot.view.model.Employee;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;

@Controller
public class HelloController {
    @GetMapping({"/", "/hello"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
    public String getEmployeeInfo() {
//		return new ModelAndView("getEmployees");
        return "getEmployees";
    }

    @RequestMapping(value = "/showEmployees", method = RequestMethod.GET)
    public String showEmployees(@RequestParam("code") String code,Model model) throws JsonProcessingException, IOException {
        ResponseEntity<String> response = null;
        System.out.println("Authorization Ccode------" + code);

        RestTemplate restTemplate = new RestTemplate();

        String credentials = "javainuse:secret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> request = new HttpEntity<String>(headers);

        String access_token_url = "http://localhost:8080/oauth/token";
        access_token_url += "?code=" + code;
        access_token_url += "&grant_type=authorization_code";
        access_token_url += "&redirect_uri=http://localhost:8090/showEmployees";

        response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        System.out.println("Access Token Response ---------" + response.getBody());

        // Get the Access Token From the recieved JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.getBody());
        String token = node.path("access_token").asText();

        String url = "http://localhost:8080/user/getEmployeesList";

        // Use the access token for authentication
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers1);

        ResponseEntity<Employee[]> employees = restTemplate.exchange(url, HttpMethod.GET, entity, Employee[].class);
        System.out.println("Returned employees information:"+employees);
        Employee[] employeeArray = employees.getBody();
        System.out.println("Returned employees information:"+Arrays.toString(employeeArray));
//        ModelAndView model = new ModelAndView("showEmployees");
//        model.addObject("employees", Arrays.asList(employeeArray));
        model.addAttribute("employees", Arrays.toString(employeeArray));
        return "showEmployees";
    }
}
