package com.sarawanak.todobe.service;

import com.sarawanak.todobe.model.Authority;
import com.sarawanak.todobe.model.User;
import com.sarawanak.todobe.repository.AuthorityRepository;
import com.sarawanak.todobe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    public User createUser(User user) {
        user.setEnabled(1);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        Authority authority = new Authority(savedUser.getUsername(), "ROLE_USER");

        authorityRepository.save(authority);

        return savedUser;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
