package com.example.task.adapter.outbound;

import com.example.task.domain.entity.Task;
import com.example.task.domain.enums.StatusEnum;
import com.example.task.domain.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskInMemoryRepository implements TaskRepository {


    private List<Task> taskList = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        return this.taskList;
    }

    @Override
    public Task findById(String id) {
        return this.taskList.stream().filter(task -> task.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    public void save(Task task) {
        this.taskList.add(task);
    }

    @Override
    public void update(Task task) {
        Task existTask = this.taskList.get(this.taskList.indexOf(findById(task.getId())));
        if (existTask.getStatus().equals(StatusEnum.DONE))
            throw new IllegalArgumentException("This task already done, you cannot modify it");

        this.taskList.set(this.taskList.indexOf(findById(task.getId())), task);
    }

    @Override
    public void changeStatus(String id, StatusEnum status) {
        this.taskList.get(this.taskList.indexOf(findById(id))).setStatus(status);
    }

    @Override
    public void delete(String id) {
        Task task = this.taskList.get(this.taskList.indexOf(findById(id)));
        if (task.getStatus().equals(StatusEnum.DONE))
            throw new IllegalArgumentException("This task already done, you cannot delete it");

        this.taskList.remove(findById(id));
    }
}
