package com.sarawanak.todobe.service;

import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.model.User;
import com.sarawanak.todobe.repository.TaskRepository;
import com.sarawanak.todobe.repository.TaskSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService service = new TaskService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void shouldGetAllTodos() {
        List<Task> stubs = getStubTasks();
        when(taskRepository.findAll()).thenReturn(stubs);

        List<Task> result = service.getAllTodos();

        assertEquals(stubs.size(), result.size());
        assertEquals(result.get(0).getId(), 2);
        assertEquals(result.get(1).getId(), 4);
    }

    @Test
    void shouldGetTodoByIdGivenAnExistingTodoId() {
        Task stub = getStubTasks().get(0);
        when(taskRepository.findById(2)).thenReturn(java.util.Optional.ofNullable(stub));

        Optional<Task> res = service.getTodoById("2");

        assertTrue(res.isPresent());
        assertEquals(res.get().getId(), stub.getId());
    }

    @Test
    void shouldReturnNullForNonExistingTodoId() {
        when(taskRepository.findById(anyInt())).thenReturn(null);

        Optional<Task> res = service.getTodoById("45");

        assertNull(res);
    }

    @Test
    void shouldGetTodosMatchingIdPriorityAndStatus() {
        List<Task> stubTasks = getStubTasks();
        TaskSpecification specification = any();
        when(taskRepository.findAll(specification)).thenReturn(stubTasks);

        List<Task> res = service.getTodosMatching("Medium", "Completed");

        assertEquals(res.size(), stubTasks.size());
    }

    @Test
    void shouldCreateTodoItemWithGivenObject() {
        Task task = getStubTasks().get(0);
        when(taskRepository.save(task)).thenReturn(task);

        Task res = service.createTask(task);

        verify(taskRepository, times(1)).save(task);

        assertEquals(task.getId(), res.getId());
    }

    @Test
    void shouldUpdateTodoItemWithGivenObject() {
        Task task = getStubTasks().get(0);
        Optional<Task> oldTask = Optional.of(getStubTasks().get(1));
        Integer taskId = task.getId();

        when(taskRepository.findById(taskId)).thenReturn(oldTask);
        when(taskRepository.save(task)).thenReturn(task);

        Task res = service.updateTask(task, taskId);

        verify(taskRepository, times(1)).save(task);
        verify(taskRepository, times(1)).findById(taskId);

        assertEquals(res.getId(), task.getId());
        assertEquals(res.getDescription(), task.getDescription());
    }

    @Test
    void shouldCreateNewTaskWhenFindByIdFails() {
        Task task = getStubTasks().get(0);
        Task oldTask = getStubTasks().get(1);
        Integer taskId = task.getId();

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        when(taskRepository.save(task)).thenReturn(task);

        Task res = service.updateTask(task, taskId);

        verify(taskRepository, times(1)).save(task);
        verify(taskRepository, times(1)).findById(taskId);
        assertEquals(res.getDescription(), task.getDescription());
    }

    @Test
    void shouldDeleteTaskWithId() {
        int taskId = 6;

        service.deleteTodo(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }

    private List<Task> getStubTasks() {
        List<Task> tasks = new ArrayList<>();
        User user = new User("sarka", "passs", 1);
        Task t1 = new Task(2, "User Task 1", 5, 0, new Date(), user);
        Task t2 = new Task(4, "User Task Hello", 10, 1, new Date(), user);

        tasks.add(t1);
        tasks.add(t2);

        return tasks;
    }
}
