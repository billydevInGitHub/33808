package com.luban.dao;

public class IndexService {
    private IndexDao dao;

    public IndexService(IndexDao indexDao){
        this.dao=indexDao;
    }

    public void service(){
        dao.test();
    }



}
