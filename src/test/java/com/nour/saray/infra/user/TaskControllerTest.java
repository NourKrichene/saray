package com.nour.saray.infra.user;

import com.nour.saray.domain.model.Status;
import com.nour.saray.domain.model.Task;
import com.nour.saray.domain.ports.user.TaskService;
import com.nour.saray.infra.user.model.TaskDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    private static final String TASK_ID = "1";
    private static final String TASK_NAME = "Task Name";
    private static final String TASK_DESCRIPTION = "Task Description";
    private static final Status TASK_STATUS = Status.NOT_DONE;
    private static final int TASK_PRIORITY = 0;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private TaskDTO taskDTO;
    private Task task;

    @BeforeEach
    public void setUp() {
        LocalDateTime creationDate = LocalDateTime.now();
        taskDTO = new TaskDTO(TASK_ID, TASK_STATUS, TASK_NAME, creationDate, TASK_DESCRIPTION, TASK_PRIORITY);
        task = new Task(TASK_ID, TASK_STATUS, TASK_NAME, creationDate, TASK_DESCRIPTION, TASK_PRIORITY);
    }

    @AfterEach
    public void tearDown() {
        reset(taskService);
    }

    @Test
    void shouldCreateTask() {
        when(taskService.create(any())).thenReturn(task);

        ResponseEntity<TaskDTO> responseEntity = taskController.createTask(taskDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDTO, responseEntity.getBody());
        verify(taskService, times(1)).create(any());
    }

    @Test
    void shouldGetAllTasks() {
        List<Task> tasks = new ArrayList<>(List.of(task, task));
        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<TaskDTO>> responseEntity = taskController.getTasks();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void shouldUpdateTask() {
        when(taskService.update(any(), any())).thenReturn(task);

        ResponseEntity<TaskDTO> responseEntity = taskController.updateTask(TASK_ID, taskDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDTO, responseEntity.getBody());
        verify(taskService, times(1)).update(any(), any());
    }

    @Test
    void shouldDeleteTask() {
        when(taskService.delete(TASK_ID)).thenReturn("deleted");

        ResponseEntity<Void> responseEntity = taskController.deleteTask(TASK_ID);

        verify(taskService, times(1)).delete(TASK_ID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}