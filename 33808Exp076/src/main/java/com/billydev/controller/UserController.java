package com.billydev.controller;


import com.billydev.entity.User;
import com.billydev.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2019-05-23
 */
@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/getUser")
    public User getUser(@RequestParam(name="name", required=false, defaultValue="World") String name){

        return userService.getUser(name);
    }

//    @RequestMapping("/getAllUser")
//    public  Object getAllUser(@RequestParam Map<String,Object> map, int page, int limit){
//        return userService.getUserPage(page,limit,map);
//    }
//
//    @RequestMapping("/register")
//    public Object register(String username,String password){
//
//        return userService.register(username,password);
//    }
}
