package com.sarawanak.todobe.service;

import com.sarawanak.todobe.model.User;
import com.sarawanak.todobe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getCurrentUser() {
        return userRepository.findById(1).get();
    }
}
