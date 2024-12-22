package com.example.learn_springboot.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    String username;
    String password;
    String firstName;
    String lastName;
}
