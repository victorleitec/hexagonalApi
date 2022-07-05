package com.example.task.domain.repository;

import com.example.task.domain.entity.Task;
import com.example.task.domain.enums.StatusEnum;

import java.util.List;

public interface TaskRepository {

    List<Task> findAll();

    Task findById(String id);

    void save(Task task);

    void update(Task task);

    void changeStatus(String id, StatusEnum status);

    void delete(String id);
}
