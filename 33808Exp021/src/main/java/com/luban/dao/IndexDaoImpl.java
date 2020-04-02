package com.luban.dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class IndexDaoImpl implements IndexDao {

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void test() {
        System.out.println(str);
        System.out.println("Impl1");
    }
}
