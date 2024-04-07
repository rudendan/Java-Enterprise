package com.example.homework6.repository;

import com.example.homework6.model.User;
import com.example.homework6.repository.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    @Autowired
    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User add(User user) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, phone_number) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhoneNumber());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return creator(resultSet);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                users.add(creator(resultSet));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?;")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public User getById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?;")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return creator(resultSet);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    private User creator(ResultSet resultSet) throws SQLException {

        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .phoneNumber(resultSet.getString("phone_number"))
                .build();
    }
}
