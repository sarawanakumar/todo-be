package com.sarawanak.todobe.controller;

import com.sarawanak.todobe.model.User;
import com.sarawanak.todobe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.findAllUsers();
    }
}
