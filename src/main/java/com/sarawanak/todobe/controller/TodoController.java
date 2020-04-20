package com.sarawanak.todobe.controller;

import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

    @GetMapping("/todo/{id}")
    public Optional<Task> getTodo(@PathVariable String id, Principal principal) {
        return taskService.getTodoById(id, principal.getName());
    }

    @GetMapping(value = "/todo")
    public List<Task> getTodosFor(
        @RequestParam(name = "user_id", required = false) Integer userId,
        @RequestParam(required = false) String priority,
        @RequestParam(required = false) String status,
        Principal principal
    ) {
        return taskService.getTodosMatching(priority, status, principal.getName());
    }

    @PostMapping(value = "/todo")
    public Optional<Task> createTask(@RequestBody Task task, Principal principal) {
        return taskService.createTask(task, principal);
    }

    @PutMapping(value = "/todo/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable Integer id) {
        return taskService.updateTask(task, id);
    }

    @DeleteMapping(value = "/todo/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTodo(id);
    }

    @GetMapping(value = "/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
}
