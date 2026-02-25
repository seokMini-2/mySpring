package com.example.minisns.controller;

import com.example.minisns.domain.User;
import com.example.minisns.dto.CreateUserRequest;
import com.example.minisns.exception.DuplicateUsernameException;
import com.example.minisns.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 도메인 만들고, 도메인을 사용할 레포지토리 만들고, 레포 사용할 서비스 만들고, 서비스 컨트롤하는 컨트롤러
// UserRepository에서는 이름 중복체크 메서드 정의
// 컨트롤러에서 PostMapping으로 사용자 생성, JSon 응답을 위해 RequestBody 사용
// RequestBody 사용을 위해 CreateUserRequest dto 정의,
// 1. http 바디 읽고 JSON으로 파싱 2. DTO 객체 생성 3. setter 호출로 맵핑된 곳에 저장
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody CreateUserRequest request) {
        return userService.create(request.getUsername());
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    // DuplicateUsernameException클래스에서 발생한 에러를 여기서 잡겠다.
    // 응답코드는 BAD_REQUEST
    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDuplicate(DuplicateUsernameException e) {
        return e.getMessage();
    }
}
