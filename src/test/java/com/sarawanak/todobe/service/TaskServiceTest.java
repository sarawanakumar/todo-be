package com.sarawanak.todobe.service;

import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.repository.TaskRepository;
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
import static org.mockito.ArgumentMatchers.anyInt;
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
        assertEquals(result.get(1).getId(), 3);
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
        when(taskRepository.findByCriteria(anyInt(), anyInt(), anyInt()))
            .thenReturn(stubTasks);

        List<Task> res = service.getTodosMatching(1,"Medium", "Completed");

        assertEquals(res.size(), stubTasks.size());
    }

    private List<Task> getStubTasks() {
        List<Task> tasks = new ArrayList<>();
        Task task1 = Task.builder().id(2).priority(5).description("User Task 1").status(0).completionDate(new Date()).userId(1).build();

        Task task2 = Task.builder().id(3).priority(1).status(1).description("Hello Task").completionDate(new Date()).userId(5).build();
        tasks.add(task1);
        tasks.add(task2);

        return tasks;
    }
}