package com.nour.saray.infra.server.repository;

import com.nour.saray.infra.server.model.Task;

import java.util.List;

public interface TaskRepository extends org.springframework.data.repository.CrudRepository<Task, String> {

    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);
}
