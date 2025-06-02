package com.security.demo.student.services;

import com.security.demo.student.dao.UserRepo;
import com.security.demo.student.model.User;
import com.security.demo.student.model.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByName(username);
        if(user == null) {
            System.out.printf("User %s not found", username);
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrinciple(user);
    }
}
