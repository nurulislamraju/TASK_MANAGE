package com.phegondev.TasksApp.controller;

import com.phegondev.TasksApp.dto.Response;
import com.phegondev.TasksApp.dto.TaskRequest;
import com.phegondev.TasksApp.dto.UserRequest;
import com.phegondev.TasksApp.entity.Task;
import com.phegondev.TasksApp.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Response<Task>> createTask(@Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }

/*
    @PostMapping
    public ResponseEntity<Response<Task>> createTask(@Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }
*/

    @PutMapping("/{id}")
    public ResponseEntity<Response<Task>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest taskRequest){

        taskRequest.setId(id);   // important
        return ResponseEntity.ok(taskService.updateTask(taskRequest));
    }
/*
    @PostMapping
    public ResponseEntity<Response<Task>> updateTask(@Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok(taskService.updateTask(taskRequest));
    }
*/


    @GetMapping
    public ResponseEntity<Response<List<Task>>> getAllMyTask(){
        return ResponseEntity.ok(taskService.getAllMyTask());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<Task>> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }


    @GetMapping("/status")
    public ResponseEntity<Response<List<Task>>> getMyTaskCompletionStatus(
            @RequestParam boolean completed
    ){
        return ResponseEntity.ok(taskService.getMyTaskCompletionStatus(completed));
    }


    @GetMapping("/priority")
    public ResponseEntity<Response<List<Task>>> getMyTaskCompletionStatus(
            @RequestParam String priority
    ){
        return ResponseEntity.ok(taskService.getMyTasksByPriority(priority));
    }





}
