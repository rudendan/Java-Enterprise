package com.example.homework6.repository.interfaces;

import com.example.homework6.model.Task;

import java.util.List;

public interface TaskRepository {

    Task save(Task task);
    List<Task> findAll();
    Task getById(Long id);
    List<Task> getByFilter(String filter, String value);
    Task updateTaskById(Long id, Task task);
}
