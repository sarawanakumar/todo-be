package com.sarawanak.todobe.service;

import com.sarawanak.todobe.model.Task;
import com.sarawanak.todobe.model.User;
import com.sarawanak.todobe.repository.TaskRepository;
import com.sarawanak.todobe.repository.TaskSpecification;
import com.sarawanak.todobe.repository.TaskSpecificationBuilder;
import com.sarawanak.todobe.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskSpecificationBuilder builder;

    @Mock
    private UserRepository userRepository;

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
        List<Task> stub = getStubTasks();
        when(taskRepository.findByUserUsername(anyString())).thenReturn(stub);

        Optional<Task> res = service.getTodoById("2", "saravaks");

        assertTrue(res.isPresent());
        assertEquals(res.get().getId(), stub.get(0).getId());
    }

    @Test
    void shouldReturnNullForNonExistingTodoId() {
        when(taskRepository.findByUserUsername(anyString())).thenReturn(List.of());

        Optional<Task> res = service.getTodoById("45", "saravaks");

        assertEquals(Optional.empty(), res);
    }

    @Test
    void shouldGetTodosMatchingIdPriorityAndStatus() {
        List<Task> stubTasks = getStubTasks();
        User user = new User("saravaks", null, 1);
        TaskSpecification specification = any();
        when(taskRepository.findAll(specification)).thenReturn(stubTasks);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));

        List<Task> res = service.getTodosMatching("Medium", "Completed", "saravaks");

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
        User user = new User("sarka", "passs", 1);
        Task task = new Task(2, "User Task 1", 5, 0, new Date(), user);
        Task oldTask = new Task(2, "User Task Hello", 10, 1, new Date(), user);
        Integer taskId = task.getId();

        TaskService taskService = Mockito.spy(service);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(oldTask));
        when(taskRepository.save(oldTask)).thenReturn(task);

        Task res = taskService.updateTask(task, taskId);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskService).updateTask(taskCaptor.capture(), any());
        verify(taskRepository, times(1)).save(task);
        verify(taskRepository, times(1)).findById(taskId);

        assertEquals(taskCaptor.getValue().getDescription(), "User Task 1");
        assertEquals(taskCaptor.getValue().getPriority(), 5);
        assertEquals(res.getId(), task.getId());
        assertEquals(res.getDescription(), task.getDescription());
    }

    @Test
    void shouldCreateNewTaskWhenFindByIdFails() {
        Task task = getStubTasks().get(0);
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
        User user = new User("saravaks", "passs", 1);
        Task t1 = new Task(2, "User Task 1", 5, 0, new Date(), user);
        Task t2 = new Task(4, "User Task Hello", 10, 1, new Date(), user);

        tasks.add(t1);
        tasks.add(t2);

        return tasks;
    }
}
