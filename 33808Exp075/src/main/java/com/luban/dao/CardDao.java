package com.luban.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface CardDao {
	@Select("select id from table1")
	public List<Map<Integer,String>> getList(String number);


}
