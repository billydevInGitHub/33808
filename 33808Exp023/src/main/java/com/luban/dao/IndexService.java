package com.luban.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class IndexService {
    private IndexDao dao;


    public void service(){
        dao.test();
    }


//    public void setDao(IndexDao dao) {
//        this.dao = dao;
//    }

    public void setLuban(IndexDao dao) {
        this.dao = dao;
    }
}
