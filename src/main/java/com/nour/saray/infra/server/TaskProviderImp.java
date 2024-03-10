package com.nour.saray.infra.server;


import com.nour.saray.domain.model.Task;
import com.nour.saray.domain.ports.server.TaskProvider;
import com.nour.saray.infra.server.mapper.TaskEntityMapper;
import com.nour.saray.infra.server.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TaskProviderImp implements TaskProvider {

    private final TaskRepository taskRepository;


    public TaskProviderImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskEntityMapper::toDomain).toList();
    }

    @Override
    public Task getTaskById(String id) {
        if (taskRepository.findById(id).isPresent())
            return TaskEntityMapper.toDomain(taskRepository.findById(id).get());
        return null;
    }

    @Override
    public Task create(Task task) {
        return TaskEntityMapper.toDomain(taskRepository.save(TaskEntityMapper.toServer(task)));
    }


    @Override
    public Task update(String id, Task taskToEdit) {
        if (taskRepository.findById(id).isPresent()) {
            com.nour.saray.infra.server.model.Task task = taskRepository.findById(id).get();
            task.setName(taskToEdit.getName());
            task.setDescription(taskToEdit.getDescription());
            task.setCreationDate(taskToEdit.getCreationDate());
            task.setStatus(taskToEdit.getStatus());
            return TaskEntityMapper.toDomain(taskRepository.save(task));
        }
        return null;
    }

    @Override
    public String delete(final String id) {
        taskRepository.deleteById(id);
        return "deleted";
    }

}
