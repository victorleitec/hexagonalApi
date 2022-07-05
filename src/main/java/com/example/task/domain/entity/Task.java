package com.example.task.domain.entity;

import com.example.task.domain.enums.StatusEnum;
import com.example.task.infrastructure.model.TaskModel;

import java.time.LocalDateTime;

public class Task {

    private String id;
    private String name;
    private String description;
    private StatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task() {
    }

    public Task(String id, String name, String description, StatusEnum status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.validate();
    }

    public Task(String id, String name, String description, StatusEnum status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.validate();
    }

    public Task(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = StatusEnum.TODO;
        this.createdAt = LocalDateTime.now();
        this.validate();
    }

    public void changeName(String name) {
        this.name = name;
        this.validate();
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeStatus(StatusEnum status) {
        if (this.status.equals(StatusEnum.DONE))
            throw new IllegalArgumentException("This task already done, you cannot modify it");

        this.status = status;
        this.validate();
    }

    private void validate() {
        if (name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("Name is required");

        if (status == null)
            throw new IllegalArgumentException("Status is required");
    }

    public TaskModel toModel() {
        return new TaskModel(
                this.id,
                this.name,
                this.description,
                this.status.toString(),
                this.createdAt,
                this.updatedAt
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
