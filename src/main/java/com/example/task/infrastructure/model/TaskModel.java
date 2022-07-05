package com.example.task.infrastructure.model;

import com.example.task.domain.entity.Task;
import com.example.task.domain.enums.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tasks")
public class TaskModel {

    @Id
    @Column(name = "cd_task")
    private String id;

    @Column(name = "nm_task", nullable = false)
    private String name;

    @Column(name = "ds_task")
    private String description;

    @Column(name = "st_task")
    private String status;

    @Column(name = "dt_createdAt")
    private LocalDateTime createdAt;

    @Column(name = "dt_updatedAt")
    private LocalDateTime updatedAt;

    public TaskModel() {
    }

    public TaskModel(String id, String name, String description, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Task toTask() {
        return new Task(
                this.id,
                this.name,
                this.description,
                StatusEnum.valueOf(this.status),
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
