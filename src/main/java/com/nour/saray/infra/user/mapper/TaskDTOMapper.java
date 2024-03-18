package com.nour.saray.infra.user.mapper;


import com.nour.saray.domain.model.Task;
import com.nour.saray.infra.user.model.TaskDTO;

public final class TaskDTOMapper {

    private TaskDTOMapper() {
    }

    public static Task toDomain(final TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }
        return new Task(taskDTO.id(),taskDTO.status(), taskDTO.name(), taskDTO.creationDate(), taskDTO.description(), taskDTO.priority());
    }

    public static TaskDTO toUser(final Task task) {
        if (task == null) {
            return null;
        }
        return new TaskDTO(task.id(),task.status(),task.name(), task.creationDate(), task.description(), task.priority());
    }
}
