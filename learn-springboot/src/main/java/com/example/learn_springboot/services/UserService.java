package com.example.learn_springboot.services;

import com.example.learn_springboot.dto.request.UserCreateRequest;
import com.example.learn_springboot.dto.request.UserUpdateRequest;
import com.example.learn_springboot.entity.User;
import com.example.learn_springboot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;

    public User createUser(UserCreateRequest request) {
        User newUser = new User();

        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }

    public User updateUser(UserUpdateRequest request, String id){
        User updateUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        updateUser.setUsername(request.getUsername());
        updateUser.setPassword(request.getPassword());
        updateUser.setFirstName(request.getFirstName());
        updateUser.setLastName(request.getLastName());
        return userRepository.save(updateUser);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
