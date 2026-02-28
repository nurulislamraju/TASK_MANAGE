package com.phegondev.TasksApp.dto;

import com.phegondev.TasksApp.enums.Priority;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
     private Long id;

     @NotBlank(message = "Title cannot be empty")
     @Size(max = 200, message = "Title must be less than 200 character")
     private String title;

     @Size(max = 500, message = "Description must be less than 500 character")
     private String description;

     @NotNull(message = "Complete Status is required")
     private Boolean completed;

     @NotNull(message = "Priority is required")
     private Priority priority;

     @FutureOrPresent(message = "Due Date must be today or future")
     private LocalDate dueDate;
}
