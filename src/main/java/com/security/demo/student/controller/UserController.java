package com.security.demo.student.controller;

import com.security.demo.student.model.User;
import com.security.demo.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User doRegister(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
