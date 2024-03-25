package com.example.homework6.service;

import com.example.homework6.converter.UserConverter;
import com.example.homework6.dto.UserDto;
import com.example.homework6.exception.NotFoundException;
import com.example.homework6.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    public static int idGenerator = 0;

    public UserDto create(UserDto userDto) {
        User user = UserConverter.toUser(userDto);
        user.setId(++idGenerator);
        users.add(user);
        return UserConverter.toUserDto(user);
    }

    public List<UserDto> getAll() {
        return users.stream().map(UserConverter::toUserDto).toList();
    }

    public void delete(int userId) {
        User user = users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst().orElseThrow(() -> new NotFoundException("There is now such user"));
        users.remove(user);
    }

    public UserDto getById(int id) {
        return UserConverter.toUserDto(users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().orElseThrow(() -> new NotFoundException("There is now such user")));
    }
}
