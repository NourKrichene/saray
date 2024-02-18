package com.nour.saray;

import java.util.List;

public interface TaskRepository extends org.springframework.data.repository.CrudRepository<Task, String> {

    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);
}
