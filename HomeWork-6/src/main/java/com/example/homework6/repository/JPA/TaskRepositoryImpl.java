package com.example.homework6.repository.JPA;

import com.example.homework6.model.Task;
import com.example.homework6.repository.interfaces.TaskRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    @ConditionalOnProperty(value = "useJPA", havingValue = "true")
    public interface TaskRepositoryImpl extends JpaRepository<Task, Long>, TaskRepository {

    @Override
    @Query("SELECT t FROM Task t JOIN t.user u WHERE CASE "
            + "WHEN :filter = 'name' THEN t.name = :value "
            + "WHEN :filter = 'description' THEN t.description = :value "
            + "WHEN :filter = 'priority' THEN t.priority = CAST(:value AS com.example.homework6.enums.TaskPriority) "
            + "WHEN :filter = 'user_id' THEN u.id = CAST(:value AS Long) "
            + "WHEN :filter = 'deadline' THEN t.deadline = CAST(:value AS LocalDate) "
            + "WHEN :filter = 'task_status' THEN t.taskStatus = CAST(:value AS com.example.homework6.enums.TaskStatus) "
            + "ELSE FALSE END")
    List<Task> getByFilter(@Param("filter") String filter, @Param("value") String value);


    @Override
    @Modifying
    @Query("UPDATE Task t SET t.name = :#{#task.name}, t.description = :#{#task.description}, t.deadline = :#{#task.deadline}," +
            " t.taskStatus = :#{#task.taskStatus}, t.priority = :#{#task.priority}, t.user = :#{#task.getUser().id} " +
            "WHERE t.id = :id")
    Task updateTaskById(@Param("id") Long id, @Param("task") Task task);
}
