package com.sarawanak.todobe.service;

import com.sarawanak.todobe.helper.PriorityHelper;
import com.sarawanak.todobe.helper.StatusHelper;
import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.repository.TaskRepository;
import com.sarawanak.todobe.repository.TaskSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Task> getTodosMatching(String priority, String status) {
        Integer priorityCode = PriorityHelper.getCodeForPriority(priority);
        Integer statusCode = StatusHelper.getCodeForStatus(status);
        TaskSpecificationBuilder builder = new TaskSpecificationBuilder();

        if (priority != null)
            builder.with("priority", priorityCode);
        if (status != null)
            builder.with("status", statusCode);

        Specification<Task> specification = builder.build();

        return taskRepository.findAll(specification);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task, Integer id) {
        return taskRepository.findById(id).map(t -> {
            Optional.ofNullable(task.getDescription())
                .ifPresent(desc -> t.setDescription(desc));
            Optional.ofNullable(task.getPriority())
                .ifPresent(priority -> t.setPriority(priority));
            Optional.ofNullable(task.getStatus())
                .ifPresent(status -> t.setStatus(status));
            Optional.ofNullable(task.getCompletionDate())
                .ifPresent(completionDate -> t.setCompletionDate(completionDate));

            return taskRepository.save(t);
        }).orElseGet(() -> taskRepository.save(task));
    }

    public void deleteTodo(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}
