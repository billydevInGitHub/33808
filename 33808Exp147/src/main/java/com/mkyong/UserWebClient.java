package com.mkyong;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class UserWebClient {

    private WebClient client = WebClient.create("http://localhost:8080");

    public List<Book> getResult() {
        return client.get().uri("/books").retrieve()
                .bodyToFlux(Book.class).collectList().block();
    }
}
