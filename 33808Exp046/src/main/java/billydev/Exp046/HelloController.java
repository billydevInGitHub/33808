package billydev.Exp046;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
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
}
