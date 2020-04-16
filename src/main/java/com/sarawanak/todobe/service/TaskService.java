package com.sarawanak.todobe.service;

import com.sarawanak.todobe.helper.PriorityHelper;
import com.sarawanak.todobe.helper.StatusHelper;
import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.repository.CustomTaskRepositoryImpl;
import com.sarawanak.todobe.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTodos() {
        return taskRepository.findAll();
    }

    public List<Task> getTodosByUserId(Integer userId, String priority, String status) {
        Integer priorityCode = PriorityHelper.getCodeForPriority(priority);
        Integer statusCode = StatusHelper.getCodeForStatus(status);

        return taskRepository.findByCriteria(userId, priorityCode, statusCode);
    }
}
