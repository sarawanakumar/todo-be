package com.sarawanak.todobe.service;

import com.sarawanak.todobe.helper.PriorityHelper;
import com.sarawanak.todobe.helper.StatusHelper;
import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.model.User;
import com.sarawanak.todobe.repository.TaskRepository;
import com.sarawanak.todobe.repository.TaskSpecificationBuilder;
import com.sarawanak.todobe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Transactional
    public Optional<Task> getTodoById(String Id, String username) {
        try {
            Integer taskId = Integer.parseInt(Id);
            List<Task> t = taskRepository.findByUserUsername(username)
                .stream()
                .filter(task -> {
                    return task.getId() == taskId;
                })
                .collect(Collectors.toList());

            return Optional.ofNullable(t.isEmpty() ? null : t.get(0));
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
            return Optional.empty();
        }
    }

    @Transactional
    public List<Task> getTodosMatching(String priority, String status, String dateString, String orderByKey,
                                       String username) {
        Integer priorityCode = PriorityHelper.getCodeForPriority(priority);
        Integer statusCode = StatusHelper.getCodeForStatus(status);

        taskSpecificationBuilder.resetCriteria();
        Optional.ofNullable(priority)
            .ifPresent(pri -> taskSpecificationBuilder.with("priority", priorityCode));
        Optional.ofNullable(status)
            .ifPresent(sta -> taskSpecificationBuilder.with("status", statusCode));
        Optional.ofNullable(dateString).ifPresent(dString -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            try {
                Date completionDate = dateFormat.parse(dateString);
                taskSpecificationBuilder.with("completionDate", completionDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        userRepository.findById(username)
            .ifPresent(user -> taskSpecificationBuilder.with("user", user));
        Optional<Sort> sort = Optional.ofNullable(orderByKey)
            .map(key -> Sort.by(Sort.Direction.ASC, key));

        Specification<Task> specification = taskSpecificationBuilder.build();

        return sort.map(el -> taskRepository.findAll(specification, el))
            .orElseGet(() -> taskRepository.findAll(specification));
    }

    @Transactional
    public Optional<Task> createTask(Task task, Principal principal) {
        Optional<User> currentUser = userRepository.findById(principal.getName());

        return currentUser.map(user -> {
            task.setUser(user);
            return taskRepository.save(task);
        });
    }

    @Transactional
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
