package com.modules.admin.controller;


import com.config.PowerEnum;
import com.config.RedisKeys;
import com.modules.admin.annotation.Power;
import com.modules.admin.service.IUserService;
import com.modules.admin.service.impl.UserServiceImpl;
import com.modules.admin.util.R;
import com.modules.admin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/getUser")
    public  Object login(@RequestParam Map<String,Object> map){
        return userService.getUser(map);
    }


    @RequestMapping("/getAllUser")
    @Power
    public  Object getAllUser(@RequestParam Map<String,Object> map,int page,int limit){
        return userService.getUserPage(page,limit,map);
    }

    @RequestMapping("/register")
    public Object register(String username,String password){

        return userService.register(username,password);
    }

    @RequestMapping("/addUser")
    public Object addUser(String username,String password,int rid){
        return  userService.addUser(username,password,rid);
    }


    @RequestMapping("/getPowerList")
    public  Object getPowerList(HttpServletRequest request){
        String login_key = request.getHeader(RedisKeys.LOGIN_KEY);
        if (StringUtils.isEmpty(login_key)){
            return  R.error("用户未登录");
        }
        Map<String,Object> user = (Map<String, Object>)redisUtil.get(login_key);
        if (user==null){
            return  R.error("用户未登录");
        }
        return userService.getPowerList((Integer)user.get("rid"));
    }


    @RequestMapping("/getRoles")
    public  Object getRoles(){

        return userService.getRoles();
    }
}
