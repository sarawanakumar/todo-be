package com.sarawanak.todobe.controller;

import com.sarawanak.todobe.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

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
    void ShouldReturnHello() {
    }

    @Test
    void shouldGetAllTodos() {
    }

    @Test
    void shouldGetTodoById() {
    }

    @Test
    void shouldGetTodosForPriorityUserIdAndStatus() {
    }
}
