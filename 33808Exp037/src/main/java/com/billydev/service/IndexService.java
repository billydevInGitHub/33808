package com.billydev.service;

import org.springframework.stereotype.Component;

@Component("indexService")
public class IndexService {
    public void service(){
        System.out.println("print from index service");
    }
}
