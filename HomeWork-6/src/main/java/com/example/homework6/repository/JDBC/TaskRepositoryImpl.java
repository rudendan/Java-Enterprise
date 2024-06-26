package com.example.homework6.repository.JDBC;

import com.example.homework6.enums.TaskPriority;
import com.example.homework6.enums.TaskStatus;
import com.example.homework6.model.Task;
import com.example.homework6.model.User;
import com.example.homework6.repository.interfaces.TaskRepository;
import com.example.homework6.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@ConditionalOnProperty(value = "useJPA", havingValue = "false")
public class TaskRepositoryImpl implements TaskRepository {

    private final Connection connection;
    private final UserRepository userRepository;

    @Autowired
    public TaskRepositoryImpl(Connection connection, UserRepository userRepository) {
        this.connection = connection;
        this.userRepository = userRepository;
    }

    @Override
    public Task save(Task task) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                "tasks(name, description, deadline, priority, task_status) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getDeadline()));
            statement.setString(4, task.getPriority().getPriority());
            statement.setString(5, task.getTaskStatus().getName());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return creator(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Task getById(Long id) {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                 return creator(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tasks.add(creator(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tasks;
    }

    @Override
    public List<Task> getByFilter(String filter, String value) {
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE " + filter + " = ?;")) {
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tasks.add(creator(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tasks;
    }

    @Override
    public Task updateTaskById(Long id, Task task) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET " +
                "name = ?, description = ?, deadline = ?, task_status = ?, priority = ?, user_id = ? " +
                "WHERE id = ?;", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getDeadline()));
            statement.setString(4, task.getTaskStatus().getName());
            statement.setString(5, task.getPriority().getPriority());
            statement.setLong(6, task.getUser().getId());
            statement.setLong(7, task.getId());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return creator(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private Task creator(ResultSet resultSet) throws SQLException {
        Task task = Task.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .deadline(resultSet.getDate("deadline").toLocalDate())
                .priority(TaskPriority.valueOf(resultSet.getString("priority")))
                .build();

        if (resultSet.getString("task_status") != null) {
            task.setTaskStatus(TaskStatus.valueOf(resultSet.getString("task_status")));
        }

        if (resultSet.getString("user_id") != null) {
            User user = userRepository.getById(Long.parseLong(resultSet.getString("user_id")));
            task.setUser(user);
        }

        return task;
    }
}
