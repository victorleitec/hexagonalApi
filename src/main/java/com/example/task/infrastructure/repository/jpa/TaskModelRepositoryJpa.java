package com.example.task.infrastructure.repository.jpa;

import com.example.task.infrastructure.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskModelRepositoryJpa extends JpaRepository<TaskModel, String> {
}
