package com.billydev.service.impl;


import com.billydev.entity.User;
import com.billydev.mapper.UserMapper;
import com.billydev.service.IUserService;
import com.billydev.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2019-05-23
 */
@Service
public class UserServiceImpl  implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(String inputName) {
        return userMapper.getUser(inputName);
    }
}
