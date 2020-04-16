package com.sarawanak.todobe.service;

import com.sarawanak.todobe.helper.PriorityHelper;
import com.sarawanak.todobe.helper.StatusHelper;
import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTodos() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTodoById(String id) {
        Integer taskId = Integer.parseInt(id);
        return taskRepository.findById(taskId);
    }

    public List<Task> getTodosMatching(Integer userId, String priority, String status) {
        Integer priorityCode = PriorityHelper.getCodeForPriority(priority);
        Integer statusCode = StatusHelper.getCodeForStatus(status);

        return taskRepository.findByCriteria(userId, priorityCode, statusCode);
    }
}
