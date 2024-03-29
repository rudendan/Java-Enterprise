package com.example.homework6.controller;

import com.example.homework6.dto.TaskDto;
import com.example.homework6.enums.TaskStatus;
import com.example.homework6.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> get() {
        return taskService.showAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @GetMapping("/{filter}/{value}")
    public <T> ResponseEntity<List<TaskDto>> getByFilter(@PathVariable String filter, @PathVariable T value) {
        return new ResponseEntity<>(taskService.showTasksByFilter(filter, value), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}/user/{userId}")
    public ResponseEntity<TaskDto> assignTaskToUser(@PathVariable int taskId, @PathVariable int userId) {
        return new ResponseEntity<>(taskService.assignTaskToUser(taskId, userId), HttpStatus.OK);
    }

    @PutMapping("/{taskId}/{status}")
    public ResponseEntity<TaskDto> changeStatus(@PathVariable int taskId, @PathVariable TaskStatus status) {
        return new ResponseEntity<>(taskService.changeStatus(taskId, status), HttpStatus.OK);
    }
}
