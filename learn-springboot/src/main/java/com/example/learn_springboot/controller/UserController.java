package com.example.learn_springboot.controller;


import com.example.learn_springboot.dto.request.UserCreateRequest;
import com.example.learn_springboot.dto.request.UserUpdateRequest;
import com.example.learn_springboot.dto.response.ApiResponse;
import com.example.learn_springboot.dto.response.UserResponse;
import com.example.learn_springboot.entity.User;
import com.example.learn_springboot.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request){
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("Successfully created user")
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers(){
       return ApiResponse.<List<UserResponse>>builder()
               .code(200)
               .result(userService.getAllUsers())
               .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findUserById(@PathVariable String id){
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .result(userService.getUserById(id))
                .build();
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request){
        return userService.updateUser(request, id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return "User deleted";
    }
}
