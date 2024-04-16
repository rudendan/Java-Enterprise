package com.example.homework6.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {

    NEW("NEW"),
    IN_WORK("IN_WORK"),
    FINISHED("FINISHED"),
    POSTPONED("POSTPONED");

    private final String name;

    TaskStatus(String name) {
        this.name = name;
    }
}
