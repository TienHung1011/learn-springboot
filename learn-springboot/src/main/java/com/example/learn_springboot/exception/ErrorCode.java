package com.example.learn_springboot.exception;


import lombok.*;

@Getter
@AllArgsConstructor

public enum ErrorCode {

    // Uncategorized_exception
    UNCATEGORIZED_EXCEPTION(444, "Uncategorized Exception"),
    // User
    USER_EXISTED(405,"User already existed"),
    USER_NOT_FOUND(404,"User not found"),
    INVALID_USERNAME(400,"Username must be at least 3 characters"),
    INVALID_PASSWORD(400,"Password must be at least 6 characters"),
    UNAUTHENTICATED(404,"Unauthenticated"),
    ;
    private final int code;
    private final String message;
}
