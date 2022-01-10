package com.modules.admin.controller;


import com.modules.admin.service.IMenuService;
import com.modules.admin.util.R;
import com.modules.admin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/getMenu")
    public R getMenu(HttpServletRequest request){
        String login_key = request.getHeader("login_key");
        if (StringUtils.isEmpty(login_key)){
            return  R.error("用户未登录");
        }
        Map<String,Object> user = (Map<String, Object>)redisUtil.get(login_key);
        if (user==null){
            return  R.error("用户未登录");
        }
        return menuService.getMenu((Integer) user.get("rid"));
    }


    @RequestMapping("/getOrder")
    public R getOrder(@RequestParam Map<String,Object> map){

        return menuService.getOrder(map);
    }



}
