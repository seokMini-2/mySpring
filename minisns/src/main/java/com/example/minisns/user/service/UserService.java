package com.example.minisns.user.service;

import com.example.minisns.user.domain.User;
import com.example.minisns.user.repository.UserRepository;
import com.example.minisns.user.dto.UserResponse;
import com.example.minisns.global.exception.DuplicateUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException("이미 존재하는 이름: " + username);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User savedUser = userRepository.save(User.create(username, encodedPassword));
        return UserResponse.toUserResponse(savedUser);
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