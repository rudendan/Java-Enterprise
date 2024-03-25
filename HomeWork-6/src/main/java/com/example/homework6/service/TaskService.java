package com.example.homework6.service;

import com.example.homework6.converter.TaskConverter;
import com.example.homework6.converter.UserConverter;
import com.example.homework6.dto.TaskDto;
import com.example.homework6.enums.TaskPriority;
import com.example.homework6.enums.TaskStatus;
import com.example.homework6.exception.NotFoundException;
import com.example.homework6.model.Task;
import com.example.homework6.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {


    private Map<Integer, Task> tasks = new HashMap<>();
    public static int idGenerator = 0;
    private UserService userService;

    public TaskService(UserService userService) {
        this.userService = userService;
    }

    public TaskDto addTask(TaskDto taskDto) {
        Task task = TaskConverter.toTask(taskDto);
        task.setId(++idGenerator);
        task.setTaskStatus(TaskStatus.NEW);
        tasks.put(task.getId(), task);
        return TaskConverter.toTaskDto(task);
    }

    public List<TaskDto> showAllTasks() {
        return tasks.values().stream().map(TaskConverter::toTaskDto).toList();
    }

    public <T> List<TaskDto> showTasksByFilter(String filter, T value) {

        List<Task> taskList = new ArrayList<>();

        switch (filter.toLowerCase()) {
            case "deadline" -> taskList = getByDeadline((LocalDate) value);
            case "priority" -> taskList = getByPriority((TaskPriority) value);
            case "taskname" -> taskList = getByTaskName((String) value);
            case "userid" -> taskList = getByUserId((int) value);
            default -> throw new NotFoundException("There is now such filter");
        }
        return taskList.stream().map(TaskConverter::toTaskDto).toList();
    }

    public TaskDto assignTaskToUser(int taskId, int userId) {
        Task task = tasks.get(taskId);
        User user = UserConverter.toUser(userService.getById(userId));

        if (task == null) {
            throw new NotFoundException("There is now such task");
        }

        task.setUser(user);
        task.setTaskStatus(TaskStatus.IN_WORK);
        return TaskConverter.toTaskDto(tasks.put(taskId, task));
    }

    public TaskDto getTaskById(int taskId) {
        return TaskConverter.toTaskDto(tasks.get(taskId));
    }

    public TaskDto changeStatus(int taskId, TaskStatus status) {
        Task task = tasks.get(taskId);

        if (task == null) {
            throw new NotFoundException("There is now such task");
        }

        task.setTaskStatus(status);
        return TaskConverter.toTaskDto(task);
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
                .filter(task -> userId == task.getUser().getId())
                .collect(Collectors.toList());
    }
}
