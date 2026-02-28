package com.phegondev.TasksApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Username is Required")
    private String userName;

    @NotBlank(message = "Password is Required")
    private String password;




}
