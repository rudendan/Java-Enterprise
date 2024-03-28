package com.example.homework6.converter;

import com.example.homework6.dto.TaskDto;
import com.example.homework6.model.Task;

public class TaskConverter {

    public static TaskDto toTaskDto(Task task) {

        TaskDto taskDto = TaskDto.builder()
                .name(task.getName())
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .priority(task.getPriority())
                .build();

        if (task.getUser() != null)
            taskDto.setUserDto(UserConverter.toUserDto(task.getUser()));
        return taskDto;
    }

    public static Task toTask(TaskDto taskDto) {

        return Task.builder()
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .priority(taskDto.getPriority())
                .deadline(taskDto.getDeadline())
                .build();
    }
}
