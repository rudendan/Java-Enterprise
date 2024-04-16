package com.example.homework6.service;

import com.example.homework6.converter.TaskConverter;
import com.example.homework6.converter.UserConverter;
import com.example.homework6.dto.TaskDto;
import com.example.homework6.enums.TaskPriority;
import com.example.homework6.enums.TaskStatus;
import com.example.homework6.exception.NotFoundException;
import com.example.homework6.model.Task;
import com.example.homework6.model.User;
import com.example.homework6.repository.dao.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public TaskDto add(TaskDto taskDto) {
        Task task = TaskConverter.toTask(taskDto);
        task.setTaskStatus(TaskStatus.NEW);

        return TaskConverter.toTaskDto(taskRepository.create(task));
    }

    public List<TaskDto> showAll() {
        return taskRepository.getAll().stream()
                .map(TaskConverter::toTaskDto).toList();
    }

    public List<TaskDto> showTasksByFilter(String filter, String value) {

        switch (filter.toLowerCase()) {
            case "deadline", "name", "priority", "user_id", "description", "task_status" -> {
                return taskRepository.getByFilter(filter, value).stream()
                        .map(TaskConverter::toTaskDto).toList();
            }
            default -> throw new NotFoundException("There is now such filter");
        }
    }

    public TaskDto assignTaskToUser(int taskId, int userId) {
        Task task = taskRepository.getById(taskId);
        User user = userService.getById(userId);


        if (task == null) {
            throw new NotFoundException("There is now such task");
        }

        task.setUser(user);
        task.setTaskStatus(TaskStatus.IN_WORK);
        return TaskConverter.toTaskDto(taskRepository.update(taskId, task));
    }

    public TaskDto getById(int taskId) {
        Task task = taskRepository.getById(taskId);

        if (task == null) {
            throw new NotFoundException("There is now such task");
        }

        return TaskConverter.toTaskDto(task);
    }

    public TaskDto update(int taskId, TaskDto taskDto) {
        Task task = taskRepository.getById(taskId);

        if (task == null) {
            throw new NotFoundException("There is now such task");
        }
        if (taskDto.getName() != null) {
            task.setName(taskDto.getName());
        }
        if (taskDto.getDescription() != null) {
            task.setDescription(taskDto.getDescription());
        }
        if (taskDto.getDeadline() != null) {
            task.setDeadline(taskDto.getDeadline());
        }
        if (taskDto.getPriority() != null) {
            task.setPriority(taskDto.getPriority());
        }
        if (taskDto.getUserDto() != null) {
            task.setUser(UserConverter.toUser(taskDto.getUserDto()));
        }
        return TaskConverter.toTaskDto(taskRepository.update(taskId, task));
    }
}
