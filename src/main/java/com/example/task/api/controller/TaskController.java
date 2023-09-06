package com.example.task.api.controller;

import com.example.task.api.dto.TaskDto;
import com.example.task.model.entity.Task;
import com.example.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping()
    public ResponseEntity get(){
        List<Task> tasks = service.getTask();
        return ResponseEntity.ok(tasks.stream().map(TaskDto::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Task> task = service.getTaskById(id);

        if(!task.isPresent()){
            return new ResponseEntity("Task not found!", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(task.map(TaskDto::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody TaskDto dto){
        try {
            Task task = converter(dto);
            task = service.save(task);
            return new ResponseEntity(task, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody TaskDto dto){
        if(!service.getTaskById(id).isPresent()){
            return new ResponseEntity("Task not found", HttpStatus.NOT_FOUND);
        }
        try {
            Task task = converter(dto);
            task.setId(id);
            service.save(task);
            return ResponseEntity.ok(task);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        Optional<Task> task = service.getTaskById(id);
        if(!task.isPresent()){
            return new ResponseEntity("Task not found", HttpStatus.NOT_FOUND);
        }
        try {
            service.delete(task.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Task converter(TaskDto dto){
        ModelMapper modelMapper = new ModelMapper();
        Task task = modelMapper.map(dto, Task.class);
        return task;
    }
}
