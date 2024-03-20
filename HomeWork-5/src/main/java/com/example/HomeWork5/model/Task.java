package com.example.HomeWork5.model;

import com.example.HomeWork5.enums.TaskPriority;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Task implements Comparable<Task>{

    //Реалізуйте можливість створювати нові завдання з вказанням назви, опису, терміну виконання та пріоритету.
    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private TaskPriority priority;
    private int userId;

    @Override
    public int compareTo(Task o) {
        return 0;
    }
}
