package com.example.jpa.controller;

import com.example.jpa.model.User;
import com.example.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllPosts(Pageable pageable) {
        return  userRepository.findAll();
    }

    @GetMapping("/usersByAge")
    public List<User> getAllPosts(@RequestParam int age) {
        return  userRepository.findByAgeLessThan(age);
    }

    @GetMapping("/usersCreation")
    public User userCreation() {
        User user =new User();
        user.setActive(1);
        user.setAge(new Random().nextInt());
        user.setFirstname(String.valueOf(new Random().nextInt()));
        user.setLastname(String.valueOf(new Random().nextInt()));
        user.setStartDate(new Date());
        return  userRepository.save(user);
    }



}
