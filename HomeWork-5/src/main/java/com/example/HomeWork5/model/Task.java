package com.example.HomeWork5.model;

import com.example.HomeWork5.enums.TaskPriority;
import com.example.HomeWork5.enums.TaskStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Task {

    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private TaskPriority priority;
    private TaskStatus taskStatus;
    private int userId;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority=" + priority +
                ", taskStatus=" + taskStatus +
                ", userId=" + userId +
                '}';
    }
}
