package com.example.task.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {


    private String task;
    private LocalDate startDate;
    private LocalDate endDate;
}
