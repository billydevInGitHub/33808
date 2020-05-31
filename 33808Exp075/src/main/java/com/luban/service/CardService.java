package com.luban.service;


import com.luban.dao.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardService {
	@Autowired
	CardDao cardDao;  //CardDao here is an interface

	public void list(){
		System.out.println("Print from CardService:"+cardDao.getList("bbb within CardService List method"));
	}
}
