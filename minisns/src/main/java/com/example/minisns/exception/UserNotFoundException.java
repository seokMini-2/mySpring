package com.example.minisns.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(Long id) {
    super("유저 없음: " + id);
  }
}
