package com.example.homework6.converter;

import com.example.homework6.dto.UserDto;
import com.example.homework6.model.User;

public class UserConverter {

    public static User toUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
