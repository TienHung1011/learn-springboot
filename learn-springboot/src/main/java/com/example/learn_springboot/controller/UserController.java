package com.example.learn_springboot.controller;


import com.example.learn_springboot.dto.request.UserCreateRequest;
import com.example.learn_springboot.dto.request.UserUpdateRequest;
import com.example.learn_springboot.entity.User;
import com.example.learn_springboot.services.UserService;
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
    public User createUser(@RequestBody UserCreateRequest request){
        return userService.createUser(request);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request){
        return userService.updateUser(request, id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return "User deleted";
    }
}
