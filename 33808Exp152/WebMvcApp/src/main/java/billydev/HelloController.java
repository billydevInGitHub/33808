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
        try {
            Thread.sleep(Constants.THREAD_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter.increment();
        return greeting;
    }


}
