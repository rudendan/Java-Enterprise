package com.example.homework6.service;

import com.example.homework6.converter.UserConverter;
import com.example.homework6.dto.UserDto;
import com.example.homework6.exception.NotFoundException;
import com.example.homework6.model.User;
import com.example.homework6.repository.interfaces.UserRepository;
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
        return UserConverter.toUserDto(userRepository.save(user));
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserConverter::toUserDto).toList();
    }

    public void delete(Long id) {
        if (getById(id) != null) {
            userRepository.deleteById(id);
        }
    }

    public User getById(Long id) {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new NotFoundException("There is now such user");
        }
        return user;
    }
}
