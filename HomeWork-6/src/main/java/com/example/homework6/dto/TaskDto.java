package com.example.homework6.dto;

import com.example.homework6.enums.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class TaskDto {

    private String name;
    private String description;
    private LocalDate deadline;
    private TaskPriority priority;
    private UserDto userDto;
}
