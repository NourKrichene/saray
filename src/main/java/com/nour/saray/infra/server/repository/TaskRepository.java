package com.nour.saray.infra.server.repository;

import com.nour.saray.domain.model.Status;
import com.nour.saray.infra.server.model.Task;

import java.util.List;

public interface TaskRepository extends org.springframework.data.repository.CrudRepository<Task, String> {


    @Override
    Task save(Task task);


    List<Task> findAllByOrderByPriority();


    List<Task> findAllByStatus (Status status);

}
