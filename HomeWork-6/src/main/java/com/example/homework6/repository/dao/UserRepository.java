package com.example.homework6.repository.dao;

import com.example.homework6.model.User;

import java.util.List;

public interface UserRepository {

    User add(User user);
    List<User> getAll();
    void delete(int id);
    User getById(int id);
}
