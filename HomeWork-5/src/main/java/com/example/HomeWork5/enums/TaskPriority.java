package com.example.HomeWork5.enums;

import lombok.Getter;

@Getter
public enum TaskPriority {

    LOW(3),
    MEDIUM(2),
    HIGH(1);

    private int priority;

    TaskPriority(int priority) {
        this.priority = priority;
    }
}
