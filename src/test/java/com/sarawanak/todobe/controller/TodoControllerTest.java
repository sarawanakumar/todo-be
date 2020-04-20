package com.sarawanak.todobe.controller;

import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.model.User;
import com.sarawanak.todobe.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TodoControllerTest {
    @InjectMocks
    TodoController todoController = new TodoController();

    @Mock
    TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnHello() {
        assertEquals(todoController.hello(), "hello");
    }

    @Test
    void shouldCreateTodoWithRequestBody() {
        User user = new User("sarka", "passs", 1);
        Task task = new Task(4, "Desc", 5, 0, new Date(), user);
        when(taskService.createTask(task)).thenReturn(task);
        Task res = todoController.createTask(task);

        verify(taskService, times(1)).createTask(task);
        assertEquals(task.getId(), res.getId());
    }

    @Test
    void shouldModifyTodoWithNewObject() {
        User user = new User("sarka", "passs", 1);
        Task task = new Task(4, "Desc", 5, 0, new Date(), user);
        Integer taskId = 9;
        when(taskService.updateTask(task, taskId)).thenReturn(task);

        Task res = todoController.updateTask(task, taskId);

        verify(taskService, times(1)).updateTask(task, taskId);
        assertEquals(task.getId(), res.getId());
    }

    @Test
    void shouldDeleteTaskWithId() {
        Integer taskId = 6;
        User user = new User("sarka", "passs", 1);
        Task task = new Task(4, "Desc", 5, 0, new Date(), user);

        when(taskService.getTodoById(taskId.toString(), "sarka")).thenReturn(java.util.Optional.of(task));

        todoController.deleteTask(taskId);

        verify(taskService, times(1)).deleteTodo(taskId);
    }

    @Test
    void shouldGetTodoById() {
    }

    @Test
    void shouldGetTodosForPriorityUserIdAndStatus() {
    }
}
