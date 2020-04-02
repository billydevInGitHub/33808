package com.luban.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    @Autowired
    private IndexDao dao;

    public IndexService(IndexDao indexDao){
        this.dao=indexDao;
    }

    public void service(){
        dao.test();
    }



}
