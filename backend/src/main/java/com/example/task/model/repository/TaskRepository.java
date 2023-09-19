package com.example.task.model.repository;

import com.example.task.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> getAllByUsuarioId(Long id);
}
