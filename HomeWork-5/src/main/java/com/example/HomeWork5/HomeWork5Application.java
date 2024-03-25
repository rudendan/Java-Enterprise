package com.example.HomeWork5;

import com.example.HomeWork5.enums.TaskPriority;
import com.example.HomeWork5.enums.TaskStatus;
import com.example.HomeWork5.model.Task;
import com.example.HomeWork5.model.User;
import com.example.HomeWork5.service.TaskService;
import com.example.HomeWork5.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class HomeWork5Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(HomeWork5Application.class, args);

		TaskService taskService = applicationContext.getBean(TaskService.class);
		UserService userService = applicationContext.getBean(UserService.class);

		Task task = Task.builder()
				.name("Task #1")
				.priority(TaskPriority.LOW)
				.deadline(LocalDate.of(2024, Month.MAY, 30))
				.description("Some task #1")
				.build();
		taskService.addTask(task);

		Task task1 = Task.builder()
				.name("Task #2")
				.priority(TaskPriority.LOW)
				.deadline(LocalDate.of(2024, Month.MARCH, 30))
				.description("Some task #2")
				.build();
		taskService.addTask(task1);

		taskService.showAllTasks();

		userService.create(new User("Den"));
		userService.create(new User("Mark"));

		taskService.assignTaskToUser(1, 2);
		taskService.assignTaskToUser(2, 1);
		System.out.println("Show all tasks: ");
		taskService.showAllTasks();
		System.out.println("Show all tasks for user with userId = 2: ");
		taskService.showTasksByFilter("userid", 2);
		System.out.println("Show all tasks with HIGH priority: ");
		taskService.showTasksByFilter("priority", TaskPriority.LOW);
		System.out.println("Show all tasks with DATE : " + LocalDate.of(2024, Month.MARCH, 30));
		taskService.showTasksByFilter("deadline", LocalDate.of(2024, Month.MARCH, 30));

		System.out.println("Show all tasks with NAME : \"Task #2\"");
		taskService.showTasksByFilter("taskname", "Task #2");
		taskService.changeStatus(2, TaskStatus.POSTPONED);
		taskService.showAllTasks();
	}

}
