package com.nour.saray.infra.server;


import com.nour.saray.domain.model.Status;
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
        return taskRepository.findAllByOrderByPriority().stream().map(TaskEntityMapper::toDomain).toList();
    }

    @Override
    public Task getTaskById(String id) {
        if (taskRepository.findById(id).isPresent())
            return TaskEntityMapper.toDomain(taskRepository.findById(id).get());
        return null;
    }

    @Override
    public Task create(Task task) {
        List<com.nour.saray.infra.server.model.Task> tasks = taskRepository.findAllByStatus(task.getStatus());
        task.setPriority(0);
        taskRepository.saveAll(tasks.stream().map(t -> {
            t.setPriority(t.getPriority() + 1);
            return t;
        }).toList());
        return TaskEntityMapper.toDomain(taskRepository.save(TaskEntityMapper.toServer(task)));
    }


    @Override
    public Task update(String id, Task taskToEdit) {
        if (taskRepository.findById(id).isPresent()) {
            com.nour.saray.infra.server.model.Task task = taskRepository.findById(id).get();
            int oldPriority = task.getPriority();
            Status oldStatus = task.getStatus();
            task.setName(taskToEdit.getName());
            task.setDescription(taskToEdit.getDescription());
            task.setCreationDate(taskToEdit.getCreationDate());
            task.setStatus(taskToEdit.getStatus());
            task.setPriority(taskToEdit.getPriority());
            if (oldPriority != task.getPriority() || oldStatus != task.getStatus()) {
                if (oldStatus == task.getStatus())
                    shiftPriorityStatusUnchanged(task, oldPriority);
                else {
                    shiftPriorityStatusChanged(task, oldPriority, oldStatus);
                }
            }
            return TaskEntityMapper.toDomain(taskRepository.save(task));
        }
        return null;
    }

    private  void shiftPriorityStatusChanged(com.nour.saray.infra.server.model.Task task, int oldPriority, Status oldStatus) {
        List<com.nour.saray.infra.server.model.Task> tasks = taskRepository.findAllByStatus(oldStatus).stream().filter(t -> !t.getId().equals(task.getId())).toList();
        taskRepository.saveAll(tasks.stream().filter(t -> t.getPriority() > oldPriority).map(t -> {
            t.setPriority(t.getPriority() - 1);
            return t;
        }).toList());
        tasks = taskRepository.findAllByStatus(task.getStatus()).stream().filter(t -> !t.getId().equals(task.getId())).toList();
        taskRepository.saveAll(tasks.stream().filter(t -> t.getPriority() >= task.getPriority()).map(t -> {
            t.setPriority(t.getPriority() + 1);
            return t;
        }).toList());

    }

    private void shiftPriorityStatusUnchanged(com.nour.saray.infra.server.model.Task task, int oldPriority) {
        List<com.nour.saray.infra.server.model.Task> tasks = taskRepository.findAllByStatus(task.getStatus()).stream().filter(t -> !t.getId().equals(task.getId())).toList();
        taskRepository.saveAll(tasks.stream().filter(t -> t.getPriority() != oldPriority).map(t -> {
            if (t.getPriority() >= task.getPriority() && t.getPriority() < oldPriority) {
                t.setPriority(t.getPriority() + 1);
            } else if (t.getPriority() <= task.getPriority() && t.getPriority() > oldPriority) {
                t.setPriority(t.getPriority() - 1);
            }
            return t;
        }).toList());
    }

    @Override
    public String delete(final String id) {
        taskRepository.findById(id).ifPresent(task -> {
            taskRepository.delete(task);
            shiftPriorityAfterDelete(task);
        });
        return "deleted";
    }

    private void shiftPriorityAfterDelete(com.nour.saray.infra.server.model.Task task) {
        List<com.nour.saray.infra.server.model.Task> tasks = taskRepository.findAllByStatus(task.getStatus());
        taskRepository.saveAll(tasks.stream().filter(t -> t.getPriority() > task.getPriority()).map(t -> {
            t.setPriority(t.getPriority() - 1);
            return t;
        }).toList());
    }

}
