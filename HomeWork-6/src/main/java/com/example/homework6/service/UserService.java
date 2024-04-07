package com.example.homework6.service;

import com.example.homework6.converter.UserConverter;
import com.example.homework6.dto.UserDto;
import com.example.homework6.exception.NotFoundException;
import com.example.homework6.model.User;
import com.example.homework6.repository.dao.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto create(UserDto userDto) {
        User user = UserConverter.toUser(userDto);
        return UserConverter.toUserDto(userRepository.add(user));
    }

    public List<UserDto> getAll() {
        return userRepository.getAll().stream().map(UserConverter::toUserDto).toList();
    }

    public void delete(int id) {
        if (getById(id) != null) {
            userRepository.delete(id);
        }
    }

    public User getById(int id) {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new NotFoundException("There is now such user");
        }
        return user;
    }
}
