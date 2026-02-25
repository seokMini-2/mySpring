package com.example.minisns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinisnsApplication {

    // 도메인 만들고, 도메인을 사용할 레포지토리 만들고, 레포 사용할 서비스 만들고, 서비스 컨트롤하는 컨트롤러
    // UserRepository에서는 이름 중복체크 메서드 정의
    // 컨트롤러에서 PostMapping으로 사용자 생성, JSon 응답을 위해 RequestBody 사용
    // RequestBody 사용을 위해 CreateUserRequest dto 정의,
    // 1. http 바디 읽고 JSON으로 파싱 2. DTO 객체 생성 3. setter 호출로 맵핑된 곳에 저장
    public static void main(String[] args) {
        SpringApplication.run(MinisnsApplication.class, args);
    }

}
