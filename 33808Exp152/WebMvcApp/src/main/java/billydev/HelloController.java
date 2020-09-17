package billydev;

import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HelloController {

    @Autowired
    Counter counter;

    @RequestMapping("/")
    public String index() {
        String greeting = "Greetings from Spring Boot!";
        System.out.println(greeting);
        counter.increment();
        return greeting;
    }

    @RequestMapping(value = "/ex/foos", headers = "key=val", method = GET)
    @ResponseBody
    public String getFoosWithHeader() {
        return "Get some Foos with Header";
    }

    @RequestMapping(
            value = "/ex/foos",
            method = GET,
            headers = "Accept=application/json")
    @ResponseBody
    public String getFoosAsJsonFromBrowser() {
        return "Get some Foos with Header Old";
    }

    @RequestMapping(value = "/ex/testrequestmethod")
    public String getMethodArguments(HttpMethod httpMethod){
        return String.valueOf(httpMethod.matches("GET"));
    }

}
