package com.example.minisns.service;

import com.example.minisns.domain.User;
import com.example.minisns.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 이름: " + username);
        }
        return userRepository.save(new User(username));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
