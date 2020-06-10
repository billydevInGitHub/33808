package com.billydev.mapper;

import com.billydev.entity.User;
import com.billydev.util.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-05-23
 */
@Component
public interface UserMapper {
    User getUser(@Param("userName") String name);

}
