package com.nour.saray.infra.server;


import com.nour.saray.domain.model.Task;
import com.nour.saray.domain.ports.server.TaskProvider;

import com.nour.saray.infra.server.mapper.TaskEntityMapper;
import com.nour.saray.infra.server.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


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
        if(taskRepository.findById(id).isPresent())
        return TaskEntityMapper.toDomain(taskRepository.findById(id).get());
        else return null;
    }

    @Override
    public Task create(Task task) {
        return TaskEntityMapper.toDomain(taskRepository.save(TaskEntityMapper.toServer(task)));
    }


    @Override
    public Task update(Task taskToEdit) {
        return TaskEntityMapper.toDomain(taskRepository.save(TaskEntityMapper.toServer(taskToEdit)));
    }

    @Override
    public String delete(final String id) {
        taskRepository.deleteById(id);
        return "deleted";
    }

}
