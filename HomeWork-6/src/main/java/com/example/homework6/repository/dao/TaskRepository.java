package com.example.homework6.repository.dao;

import com.example.homework6.model.Task;

import java.util.List;

public interface TaskRepository {

    Task create(Task task);
    List<Task> getAll();
    Task getById(int id);
    List<Task> getByFilter(String filter, String value);
    Task update(int id, Task task);
}
