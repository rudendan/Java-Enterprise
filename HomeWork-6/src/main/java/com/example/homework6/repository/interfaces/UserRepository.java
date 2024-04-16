package com.example.homework6.repository.interfaces;

import com.example.homework6.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);
    List<User> findAll();
    void deleteById(Long id);
    User getById(Long id);
}
