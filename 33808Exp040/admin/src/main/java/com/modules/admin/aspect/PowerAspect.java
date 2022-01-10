package com.modules.admin.aspect;

import com.config.PowerEnum;
import com.config.RedisKeys;
import com.modules.admin.annotation.Power;
import com.modules.admin.mapper.MenuMapper;
import com.modules.admin.util.R;
import com.modules.admin.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 需要咨询java高级VIP课程的同学可以加安其拉老师的QQ：3164703201
 * 需要视频资料的可以添加白浅老师的QQ：2207192173
 * author：鲁班学院-商鞅老师
 */
@Aspect
@Component
public class PowerAspect {


    @Autowired
    HttpServletRequest request;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RedisUtil redisUtil;

    @Around("@annotation(com.modules.admin.annotation.Power)")
    public Object permission(ProceedingJoinPoint point) throws Throwable {
        String requestURI = request.getRequestURI();
//        User loginUser = Utils.getLoginUser();
        String login_key = request.getHeader(RedisKeys.LOGIN_KEY);
        if (StringUtils.isEmpty(login_key)){
            return  R.error("用户未登录");
        }
        Map<String,Object> user = (Map<String, Object>)redisUtil.get(login_key);
        if (user==null){
            return  R.error("用户未登录");
        }
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Power permission = method.getAnnotation(Power.class);
        List<String> acceptUrls=null;
        //TODO  将查询到的结果存入缓存
//        List<String> list = (List<String>) redisUtil.get(RedisKeys.POWER_KEY+user.get("id") + "");
//        if (list != null) {
//            acceptUrls = list;
//        }else{
            acceptUrls = menuMapper.selectMenuPower((Integer) user.get("rid"));
//            redisUtil.set(RedisKeys.POWER_KEY+ user.get("id")+"",acceptUrls);
//        }
        if (acceptUrls!=null&&acceptUrls.contains(requestURI)) {
            return point.proceed(point.getArgs());
        } else {
            return R.error("没有权限");
        }

    }

}
