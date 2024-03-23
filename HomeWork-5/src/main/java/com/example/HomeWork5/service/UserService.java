package com.example.HomeWork5.service;

import com.example.HomeWork5.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private int idGenerator = 0;

    public void create(User user) {
        user.setId(++idGenerator);
        users.add(user);
    }

    public void delete(int userId) {
        User user = users.stream().filter(u -> u.getId() == userId)
                .findFirst().orElseThrow(null);
        if (user != null) {
            users.remove(user);
        } else {
            System.out.println("No such user");
        }
    }

    public User getById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
    }

    public List<User> getAllUsers() {
        return users;
    }
}
