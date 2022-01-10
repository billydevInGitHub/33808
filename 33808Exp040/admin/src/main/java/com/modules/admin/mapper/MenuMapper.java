package com.modules.admin.mapper;

import com.modules.admin.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-05-23
 */
public interface MenuMapper extends BaseMapper<Menu> {

    public List<Menu> getMenuByRole(Map<String,Object> map);

    public  List<String> selectMenuPower(int rid);
}
