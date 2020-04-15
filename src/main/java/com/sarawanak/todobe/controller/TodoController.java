package com.sarawanak.todobe.controller;

import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/todo")
    public List<Task> getAllTodos() {
        return taskService.getAllTodos();
    }
}
