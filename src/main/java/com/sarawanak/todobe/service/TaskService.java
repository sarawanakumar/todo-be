package com.sarawanak.todobe.service;

import com.sarawanak.todobe.helper.PriorityHelper;
import com.sarawanak.todobe.helper.StatusHelper;
import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.model.User;
import com.sarawanak.todobe.repository.TaskRepository;
import com.sarawanak.todobe.repository.TaskSpecificationBuilder;
import com.sarawanak.todobe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskSpecificationBuilder taskSpecificationBuilder;

    public List<Task> getAllTodos() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTodoById(String Id, String username) {
        Integer taskId = Integer.parseInt(Id);
        List<Task> t = taskRepository.findByUserUsername(username)
            .stream()
            .filter(task -> {
                return task.getId() == taskId;
            })
            .collect(Collectors.toList());

        return Optional.ofNullable(t.isEmpty() ? null : t.get(0));
    }

    public List<Task> getTodosMatching(String priority, String status, String username) {
        Integer priorityCode = PriorityHelper.getCodeForPriority(priority);
        Integer statusCode = StatusHelper.getCodeForStatus(status);

        Optional<User> currentUser =  userRepository.findById(username);
        Optional.ofNullable(priority).ifPresent(pri -> taskSpecificationBuilder.with("priority", priorityCode));
        Optional.ofNullable(status).ifPresent(sta -> taskSpecificationBuilder.with("status", statusCode));
        currentUser.ifPresent(user -> taskSpecificationBuilder.with("user", user));

        Specification<Task> specification = taskSpecificationBuilder.build();

        return taskRepository.findAll(specification);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task, Integer id) {
        return taskRepository.findById(id).map(t -> {
            Optional.ofNullable(task.getDescription()).ifPresent(desc -> t.setDescription(desc));
            Optional.ofNullable(task.getPriority()).ifPresent(priority -> t.setPriority(priority));
            Optional.ofNullable(task.getStatus()).ifPresent(status -> t.setStatus(status));
            Optional.ofNullable(task.getCompletionDate()).ifPresent(completionDate -> t.setCompletionDate(completionDate));

            return taskRepository.save(t);
        }).orElseGet(() -> taskRepository.save(task));
    }

    public void deleteTodo(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}
