package com.example.task.adapter.outbound;

import com.example.task.domain.entity.Task;
import com.example.task.domain.enums.StatusEnum;
import com.example.task.domain.repository.TaskRepository;
import com.example.task.infrastructure.model.TaskModel;
import com.example.task.infrastructure.repository.jpa.TaskModelRepositoryJpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TaskRepositoryImpl implements TaskRepository {

    private final TaskModelRepositoryJpa repositoryJpa;

    public TaskRepositoryImpl(TaskModelRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public List<Task> findAll() {
        return repositoryJpa.findAll().stream()
                .map(TaskModel::toTask)
                .collect(Collectors.toList());
    }

    @Override
    public Task findById(String id) {
        return repositoryJpa.findById(id)
                .map(TaskModel::toTask)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    public void save(Task task) {
        TaskModel taskModel = task.toModel();
        repositoryJpa.save(taskModel);
    }

    public void update(Task task) {
        repositoryJpa.findById(task.getId())
                .map(taskModel -> {
                    taskModel.setName(task.getName());
                    taskModel.setDescription(task.getDescription());
                    taskModel.setUpdatedAt(LocalDateTime.now());
                    return repositoryJpa.save(taskModel);
                }).orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    public void changeStatus(String id, StatusEnum status) {
        repositoryJpa.findById(id)
                .map(taskModel -> {
                    taskModel.setStatus(status.toString().toUpperCase());
                    taskModel.setUpdatedAt(LocalDateTime.now());
                    return repositoryJpa.save(taskModel);
                }).orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    public void delete(String id) {
        TaskModel taskModel = repositoryJpa.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        if (taskModel.getStatus().equalsIgnoreCase(StatusEnum.DONE.toString()))
            throw new IllegalArgumentException("This task already done, you cannot delete it");

        repositoryJpa.delete(taskModel);
    }
}
