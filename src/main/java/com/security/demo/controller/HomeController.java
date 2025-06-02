package com.security.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String HomePage(HttpServletRequest request) {
        return "Home Page"+request.getSession().getId();
    }
}
