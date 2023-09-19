package com.example.task.service;

import com.example.task.model.entity.Task;
import com.example.task.model.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) { this.repository = repository; }

    public List<Task> getTask() { return repository.findAll(); }

    public Optional<Task> getTaskById(Long id) { return repository.findById(id); }

    @Transactional
    public Task save(@Valid Task task) {
        return repository.save(task);
    }

    @Transactional
    public void delete(Task task){
        Objects.requireNonNull(task.getId());
        repository.delete(task);
    }

    public List<Task> getTaskByUserId(Long id){ return repository.getAllByUsuarioId(id); }
}
