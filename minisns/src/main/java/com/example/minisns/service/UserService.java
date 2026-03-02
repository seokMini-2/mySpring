package com.example.minisns.service;

import com.example.minisns.domain.User;
import com.example.minisns.dto.UserResponse;
import com.example.minisns.exception.DuplicateUsernameException;
import com.example.minisns.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException("이미 존재하는 이름: " + username);
        }
        User user = userRepository.save(new User(username));
        return UserResponse.toUserResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return findAll()
                .stream()
                .map(UserResponse::toUserResponse)
                .toList();
    }

    private List<User> findAll() {
        return userRepository.findAll();
    }

}
