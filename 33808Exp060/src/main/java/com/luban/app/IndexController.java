package com.luban.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/testcontroller")
    public String index(){
        System.out.println("index---------");
    return "testcontroller";
    }
}
