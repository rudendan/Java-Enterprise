package com.example.homework6.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private String name;
    private String phoneNumber;
}
