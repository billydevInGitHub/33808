package com.tuling.sb10;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sb10Application {

    public static void main(String[] args) {

//        SpringApplication.run(Sb10Application.class, args);

        SpringApplication app = new SpringApplication(Sb10Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}

