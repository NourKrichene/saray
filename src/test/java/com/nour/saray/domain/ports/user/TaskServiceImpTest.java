package com.nour.saray.domain.ports.user;

import com.nour.saray.domain.model.Status;
import com.nour.saray.domain.model.Task;
import com.nour.saray.domain.ports.server.TaskProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TaskServiceImpTest {

    private static final String TASK_ID = "1";
    private static final String TASK_DESCRIPTION = "Description 1";
    private static final Status TASK_STATUS = Status.NOT_DONE;

    @Mock
    private TaskProvider taskProvider;

    private TaskServiceImp taskServiceImp;
    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskServiceImp = new TaskServiceImp(taskProvider);
        task = new Task(TASK_ID, TASK_STATUS, "Task 1", null, TASK_DESCRIPTION, 0);
    }

    @Test
    void shouldReturnTasksFromProvider() {
        List<Task> tasks = List.of(task, task);
        when(taskProvider.getAllTasks()).thenReturn(tasks);

        List<Task> result = taskServiceImp.getAllTasks();

        assertEquals(tasks, result);
    }

    @Test
    void shouldReturnTaskFromProvider() {
        when(taskProvider.getTaskById(TASK_ID)).thenReturn(task);

        Task result = taskServiceImp.getTaskById(TASK_ID);

        assertEquals(task, result);
    }

    @Test
    void shouldReturnCreatedTaskFromProvider() {
        when(taskProvider.create(task)).thenReturn(task);

        Task result = taskServiceImp.create(task);

        assertEquals(task, result);
    }

    @Test
    void shouldReturnUpdatedTaskFromProvider() {
        when(taskProvider.update(TASK_ID, task)).thenReturn(task);

        Task result = taskServiceImp.update(TASK_ID, task);

        assertEquals(task, result);
    }

    @Test
    void shouldReturnMessageFromProviderWhenDeleted() {
        when(taskProvider.delete(TASK_ID)).thenReturn("deleted");

        String result = taskServiceImp.delete(TASK_ID);

        assertEquals("deleted", result);
    }
}