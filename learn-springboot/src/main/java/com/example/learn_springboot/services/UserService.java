package com.example.learn_springboot.services;

import com.example.learn_springboot.dto.request.UserCreateRequest;
import com.example.learn_springboot.dto.request.UserUpdateRequest;
import com.example.learn_springboot.dto.response.UserResponse;
import com.example.learn_springboot.entity.User;
import com.example.learn_springboot.exception.AppException;
import com.example.learn_springboot.exception.ErrorCode;
import com.example.learn_springboot.mapper.UserMapper;
import com.example.learn_springboot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    public void validateUser(String username) {
        if(userRepository.existsByUsername(username)) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }
    public User createUser(UserCreateRequest request) {
        validateUser(request.getUsername());
        User newUser = userMapper.toUser(request);
        return userRepository.save(newUser);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public User updateUser(UserUpdateRequest request, String id){
        User updateUser = userRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        validateUser(request.getUsername());
        if(request.getUsername() != null){
            updateUser.setUsername(request.getUsername());
        }
        if(request.getPassword() != null){
            updateUser.setPassword(request.getPassword());
        }
        if(request.getFirstName()!= null){
            updateUser.setFirstName(request.getFirstName());
        }
        if(request.getLastName()!= null){
            updateUser.setLastName(request.getLastName());
        }
        return userRepository.save(updateUser);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
