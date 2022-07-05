package com.example.task.domain.factory;

import com.example.task.domain.entity.Task;
import com.example.task.domain.enums.StatusEnum;

import java.util.UUID;

public class TaskFactory {

    public static Task create(String name, String description) {
        return new Task(UUID.randomUUID().toString(), name, description);
    }

    public static Task update(String id, String name, String description) {
        return new Task(id, name, description);
    }

    public static Task changeStatus(String id, String name, String description, StatusEnum status) {
        return new Task(id, name, description, status);
    }
}
