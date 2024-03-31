package com.nour.saray.infra.server.repository;

import com.nour.saray.domain.model.Status;
import com.nour.saray.infra.server.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends org.springframework.data.repository.CrudRepository<Task, String> {


    @Override
    Task save(Task task);

    @Override
    Optional<Task> findById(String id);


    List<Task> findByUserIdOrderByPriority(String userId);


    List<Task> findByUserIdAndStatus(String userId,Status status);

}
