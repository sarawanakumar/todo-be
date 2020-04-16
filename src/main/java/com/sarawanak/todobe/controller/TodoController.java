package com.sarawanak.todobe.controller;

import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/todo/{id}")
    public Optional<Task> getTodo(@PathVariable String id) {
        return taskService.getTodoById(id);
    }

    @GetMapping(value = "/todo/filter")
    public List<Task> getTodosFor(
        @RequestParam(name = "user_id", required = false) Integer userId,
        @RequestParam(required = false) String priority,
        @RequestParam(required = false) String status
    ) {
        return taskService.getTodosMatching(userId, priority, status);
    }
}
