package com.security.demo.student.dao;

import com.security.demo.student.model.Student;
import com.security.demo.student.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByName(String username);
}
