package com.nour.saray.domain.ports.user;


import com.nour.saray.domain.model.Task;
import com.nour.saray.domain.ports.server.TaskProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TaskServiceImp implements TaskService {

    private final TaskProvider taskProvider;

    public TaskServiceImp(TaskProvider taskProvider) {
        this.taskProvider = taskProvider;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskProvider.getAllTasks();
    }

    @Override
    public Task getTaskById(String id) {
        return taskProvider.getTaskById(id);
    }

    @Override
    public Task create(Task task) {
        task.setCreationDate(LocalDateTime.now());
        return taskProvider.create(task);
    }

    @Override
    public Task update(Task task) {
        return taskProvider.update(task);
    }

    @Override
    public String delete(String id) {
       return  taskProvider.delete(id);
    }


}
