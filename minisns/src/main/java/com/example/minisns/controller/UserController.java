package com.example.minisns.controller;

import com.example.minisns.domain.User;
import com.example.minisns.dto.CreateUserRequest;
import com.example.minisns.dto.UserResponse;
import com.example.minisns.exception.DuplicateUsernameException;
import com.example.minisns.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* ####1####
도메인 만들고, 도메인을 사용할 레포지토리 만들고, 레포 사용할 서비스 만들고, 서비스 컨트롤하는 컨트롤러
UserRepository에서는 이름 중복체크 메서드 정의
컨트롤러에서 PostMapping으로 사용자 생성, JSon 응답을 위해 RequestBody 사용
RequestBody 사용을 위해 CreateUserRequest dto 정의,
1. http 바디 읽고 JSON으로 파싱 2. DTO 객체 생성 3. setter 호출로 맵핑된 곳에 저장
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* ####3-1####
    @PostMapping("/users")
    public User createUser(@RequestBody CreateUserRequest request) {
        return userService.create(request.getUsername());
    }
    기존의 createUser는 User Entity를 그대로 전부 다 반환하기 때문에 보안정보가 있는
    필드가 보이게 되면 위험함. 이에 따라서 UserResponse DTO를 생성 후 이걸 반환
     */
    @PostMapping("/users")
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        User user = userService.create(request.getUsername());
        return toUser(user);
    }

    /* ####3-2####
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }
    GET또한 마찬가지로 응답DTO 타입의 List 로 반환시킨다.
     */
    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.findAll()
                .stream()
                .map(this::toUser)
                .toList();
    }

    /* ####2#####
    DuplicateUsernameException클래스에서 발생한 에러를 여기서 잡겠다.
    응답코드는 BAD_REQUEST
     */
    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDuplicate(DuplicateUsernameException e) {
        return e.getMessage();
    }

    private UserResponse toUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername());
    }
}
