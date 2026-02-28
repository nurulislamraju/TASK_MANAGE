package com.phegondev.TasksApp.service;

import com.phegondev.TasksApp.dto.Response;
import com.phegondev.TasksApp.dto.TaskRequest;
import com.phegondev.TasksApp.entity.Task;

import java.util.List;

public interface TaskService {
    Response<Task>createTask(TaskRequest taskRequest);
    Response<List<Task>>getAllMyTask();
    Response<Task> getTaskById(Long id);
    Response<Task>updateTask(TaskRequest taskRequest);
    Response<Void> deleteTask(Long id);
    Response<List<Task>>getMyTaskCompletionStatus(boolean completed);
    Response<List<Task>> getMyTasksByPriority(String priority);


}
