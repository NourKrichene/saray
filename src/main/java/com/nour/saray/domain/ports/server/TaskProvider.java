package com.nour.saray.domain.ports.server;


import com.nour.saray.domain.model.Task;

import java.util.List;

public interface TaskProvider {

    List<Task> getAllTasks();

    Task getTaskById(final String id);

    Task create(Task task);

    Task update(String id, Task task);

    String delete(String id);
}
