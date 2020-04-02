package com.luban.dao;

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
