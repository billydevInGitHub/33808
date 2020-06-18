package billydev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


//@RestController
@Controller
public class TestController {

    @GetMapping
    @ResponseBody
    public String testMsgProcess(){
        return "this is just test";
    }
}
