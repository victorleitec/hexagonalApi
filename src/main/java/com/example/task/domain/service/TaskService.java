package com.example.task.domain.service;

import com.example.task.adapter.outbound.TaskRepositoryImpl;
import com.example.task.domain.entity.Task;
import com.example.task.domain.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepositoryImpl repository) {
        this.repository = repository;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task findById(String id) {
        return repository.findById(id);
    }

    public Task create(Task task) {
        repository.save(task);
        return task;
    }

    public Task update(Task updatedTask) {
        var task = repository.findById(updatedTask.getId());
        task.changeName(updatedTask.getName());
        task.changeDescription(updatedTask.getDescription());
        task.setUpdatedAt(LocalDateTime.now());

        repository.update(task);
        return task;
    }

    public Task changeStatus(Task updatedTask) {
        var task = repository.findById(updatedTask.getId());
        task.changeStatus(updatedTask.getStatus());
        task.setUpdatedAt(LocalDateTime.now());

        repository.changeStatus(updatedTask.getId(), updatedTask.getStatus());
        return task;
    }

    public void delete(String id) {
        repository.delete(id);
    }

}
