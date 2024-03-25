package com.nour.saray.infra.server;

import com.nour.saray.domain.model.Status;
import com.nour.saray.domain.model.Task;
import com.nour.saray.infra.server.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.reset;

class TaskProviderImpTest {

    private static final String TASK_ID = "1";
    private static final String TASK_NAME = "Task Name";
    private static final String TASK_DESCRIPTION = "Task Description";
    private static final Status TASK_STATUS = Status.NOT_DONE;
    private static final int TASK_PRIORITY = 0;

    private TaskProviderImp taskProvider;

    @Mock
    private TaskRepository taskRepository;

    private Task task;
    private com.nour.saray.infra.server.model.Task taskEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        taskProvider = new TaskProviderImp(taskRepository);

        LocalDateTime creationDate = LocalDateTime.now();
        task = new Task(TASK_ID, TASK_STATUS, TASK_NAME, creationDate, TASK_DESCRIPTION, TASK_PRIORITY);
        taskEntity = new com.nour.saray.infra.server.model.Task(TASK_ID, TASK_STATUS, TASK_NAME, creationDate, TASK_DESCRIPTION, TASK_PRIORITY);
    }

    @AfterEach
    public void tearDown() {
        reset(taskRepository);
    }

    @Test
    void shouldReturnAllTasks() {
        List<com.nour.saray.infra.server.model.Task> tasks = new ArrayList<>(List.of(taskEntity, taskEntity));
        when(taskRepository.findAllByOrderByPriority()).thenReturn(tasks);

        List<Task> tasksFromProvider = taskProvider.getAllTasks();

        assertNotNull(tasksFromProvider);
        assertEquals(tasks.size(), tasksFromProvider.size());
        assertTaskEquals(taskEntity, tasksFromProvider.get(0));
        assertTaskEquals(taskEntity, tasksFromProvider.get(1));
    }

    @Test
    void shouldReturnTaskById() {
        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(taskEntity));

        Task taskFromProvider = taskProvider.getTaskById(TASK_ID);

        assertNotNull(taskFromProvider);
        assertTaskEquals(taskEntity, taskFromProvider);
    }

    @Test
    void shouldCreateTask() {
        when(taskRepository.save(any())).thenReturn(taskEntity);

        Task createdTask = taskProvider.create(task);

        assertTaskEquals(taskEntity, createdTask);
    }

    @Test
    void shouldUpdateTask() {
        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(taskEntity));
        when(taskRepository.save(taskEntity)).thenReturn(taskEntity);

        Task updatedTask = taskProvider.update(TASK_ID, task);

        assertNotNull(updatedTask);
        assertTaskEquals(taskEntity, updatedTask);
    }

    private void assertTaskEquals(com.nour.saray.infra.server.model.Task taskEntity, Task task) {
        assertEquals(taskEntity.getId(), task.id());
        assertEquals(taskEntity.getName(), task.name());
        assertEquals(taskEntity.getDescription(), task.description());
        assertEquals(taskEntity.getStatus(), task.status());
        assertEquals(taskEntity.getPriority(), task.priority());
    }
}