package com.example.task.adapter.inbound;

import com.example.task.adapter.outbound.TaskRepositoryImpl;
import com.example.task.domain.entity.Task;
import com.example.task.domain.enums.StatusEnum;
import com.example.task.domain.factory.TaskFactory;
import com.example.task.domain.service.TaskService;
import com.example.task.infrastructure.repository.jpa.TaskModelRepositoryJpa;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskModelRepositoryJpa repositoryJpa) {
        var repository = new TaskRepositoryImpl(repositoryJpa);
        this.service = new TaskService(repository);
    }

    @GetMapping
    public List<Task> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Task getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Task create(@RequestBody TaskDto taskDto) {
        return service.create(TaskFactory.create(taskDto.name, taskDto.description));
    }

    @PutMapping("{id}")
    public Task update(@PathVariable String id, @RequestBody TaskDto taskDto) {
        return service.update(TaskFactory.update(id, taskDto.name, taskDto.description));
    }

    @PutMapping("{id}/status")
    public Task updateStatus(@PathVariable String id, @RequestBody ChangeStatusDto statusDto) {
        Task task = service.findById(id);
        StatusEnum updatedStatus = StatusEnum.valueOf(statusDto.status.toUpperCase());

        return service.changeStatus(TaskFactory.changeStatus(id, task.getName(), task.getDescription(), updatedStatus));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
