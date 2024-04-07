package com.example.homework6.enums;

import lombok.Getter;

@Getter
public enum TaskPriority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private final String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }
}
