package com.example.homework6.model;

import com.example.homework6.enums.TaskPriority;
import com.example.homework6.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private TaskPriority priority;
    private TaskStatus taskStatus;
    private User user;
}
