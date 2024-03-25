package com.example.HomeWork5.service;

import com.example.HomeWork5.enums.TaskPriority;
import com.example.HomeWork5.enums.TaskStatus;
import com.example.HomeWork5.model.Task;
import com.example.HomeWork5.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private Map<Integer, Task> tasks = new HashMap<>();
    private int idGenerator = 0;
    private UserService userService;

    public TaskService(UserService userService) {
        this.userService = userService;
    }

    public void addTask(Task task) {
        task.setId(++idGenerator);
        task.setTaskStatus(TaskStatus.NEW);
        tasks.put(task.getId(), task);
    }

    public void showAllTasks() {
        for (Task value : tasks.values()) {
            System.out.println(value);
        }
    }

    public <T> void showTasksByFilter(String filter, T value) {

        switch (filter.toLowerCase()) {
            case "deadline" -> getByDeadline((LocalDate) value).forEach(System.out::println);
            case "priority" -> getByPriority((TaskPriority) value).forEach(System.out::println);
            case "taskname" -> getByTaskName((String) value).forEach(System.out::println);
            case "taskid" -> System.out.println(getTaskById((int) value));
            case "userid" -> getByUserId((int) value).forEach(System.out::println);
            default -> System.out.println("Incorrect filter");
        }

    }

    public void assignTaskToUser(int taskId, int userId) {
        Task task = tasks.get(taskId);
        User user = userService.getById(userId);

        if (task != null && user != null) {
            task.setUserId(userId);
            task.setTaskStatus(TaskStatus.IN_WORK);
            tasks.put(taskId, task);
        } else {
            System.out.println("Task or user not found");
        }
    }

    public Task getTaskById(int taskId) {
        return tasks.get(taskId);
    }
    public void changeStatus(int taskId, TaskStatus status) {
        Task task = tasks.get(taskId);

        if (task != null) {
            task.setTaskStatus(status);
        } else {
            System.out.println("Task not found");
        }
    }

    private List<Task> getByDeadline(LocalDate localDate) {
        return tasks.values().stream()
                .filter(task -> task.getDeadline().isEqual(localDate))
                .toList();
    }

    private List<Task> getByPriority(TaskPriority priority) {
        return tasks.values().stream()
                .filter(task -> task.getPriority().getPriority() == priority.getPriority())
                .collect(Collectors.toList());
    }

    private List<Task> getByTaskName(String name) {
        return tasks.values().stream()
                .filter(task -> name.equals(task.getName()))
                .collect(Collectors.toList());
    }

    public List<Task> getByUserId(int userId) {

        return tasks.values().stream()
                .filter(task -> userId == task.getUserId())
                .collect(Collectors.toList());
    }
}
