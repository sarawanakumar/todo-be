package com.sarawanak.todobe.service;

import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTodos() {
        return taskRepository.findAll();
    }
}
