package com.billydev.controller;

import com.billydev.domain.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @GetMapping ("/books")
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("book1", "book1name", "100", "authordId1"));
        return books;
    }
}
