package com.example.task.api.dto;

import com.example.task.model.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String taskDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long usuarioId;

    public static TaskDto create(Task task){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(task, TaskDto.class);
    }
}
