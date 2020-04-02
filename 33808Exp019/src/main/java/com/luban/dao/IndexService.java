package com.luban.dao;

public class IndexService {
    private IndexDao dao;

    public void service(){
        dao.test();
    }

    public void setDao(IndexDao dao) {
        this.dao = dao;
    }
}
