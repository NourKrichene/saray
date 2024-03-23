package com.nour.saray.infra.user;

import com.nour.saray.domain.model.Status;
import com.nour.saray.domain.model.Task;
import com.nour.saray.domain.ports.user.TaskService;
import com.nour.saray.infra.user.model.TaskDTO;
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

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void createTask() {
        LocalDateTime creationDate = LocalDateTime.now();
        TaskDTO taskDTO = new TaskDTO("1", Status.NOT_DONE, "Task 1", creationDate, "Description", 1);
        Task createdTask = new Task("1", Status.NOT_DONE, "Task 1", creationDate, "Description", 1);
        when(taskService.create(any())).thenReturn(createdTask);

        ResponseEntity<TaskDTO> responseEntity = taskController.createTask(taskDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDTO, responseEntity.getBody());
        verify(taskService, times(1)).create(any());
    }

    @Test
    void getTasks() {
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime creationDate2 = LocalDateTime.now();
        List<Task> tasks = new ArrayList<>(
                List.of(new Task("1", Status.NOT_DONE, "Task 1", creationDate, "Description", 1),
                        new Task("2", Status.NOT_DONE, "Task 2", creationDate2, "Description", 2)));
        when(taskService.getAllTasks()).thenReturn(tasks);
        List<TaskDTO> taskDTOs = new ArrayList<>(
                List.of(new TaskDTO("1", Status.NOT_DONE, "Task 1", creationDate, "Description", 1),
                        new TaskDTO("2", Status.NOT_DONE, "Task 2", creationDate2, "Description", 2)));

        ResponseEntity<List<TaskDTO>> responseEntity = taskController.getTasks();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDTOs, responseEntity.getBody());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void updateTask() {
        LocalDateTime creationDate = LocalDateTime.now();
        TaskDTO taskDTO = new TaskDTO("1", Status.NOT_DONE, "Task 2", creationDate, "Description 2", 1);
        Task updatedTask = new Task("1", Status.NOT_DONE, "Task 2", creationDate, "Description 2", 1);
        when(taskService.update(any(), any())).thenReturn(updatedTask);

        ResponseEntity<TaskDTO> responseEntity = taskController.updateTask("1", taskDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDTO, responseEntity.getBody());
        verify(taskService, times(1)).update(any(), any());
    }

    @Test
    void deleteTask() {
        String taskId = "1";
        when(taskService.delete(taskId)).thenReturn("deleted");

        ResponseEntity<Void> responseEntity = taskController.deleteTask(taskId);

        verify(taskService, times(1)).delete(taskId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
