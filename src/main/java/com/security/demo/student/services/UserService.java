package com.security.demo.student.services;


import com.security.demo.student.dao.UserRepo;
import com.security.demo.student.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user) {

        user.setPassword(encoder.encode(user.getPassword()));   //stores password in bcrypt text
        return userRepo.save(user);
    }
}
