package com.security.demo.student.controller;

import com.security.demo.student.model.User;
import com.security.demo.student.services.JWTService;
import com.security.demo.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public User doRegister(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String doLogin(@RequestBody User user) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getName());
        }
        else {
            return "Login failed";
        }
    }
}
