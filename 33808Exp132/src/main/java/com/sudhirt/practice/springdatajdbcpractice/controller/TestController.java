package com.sudhirt.practice.springdatajdbcpractice.controller;

import com.sudhirt.practice.springdatajdbcpractice.entity.Book;
import com.sudhirt.practice.springdatajdbcpractice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/getBooks")
    List<Book> getBooks(){
        Iterable<Book> itera=bookRepository.findAll();
        List<Book> returnObject = new ArrayList<>();
        itera.forEach((e)->returnObject.add(e));
        return returnObject;
    }
}
