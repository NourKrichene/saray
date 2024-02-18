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
        return new Task(taskDTO.getId(),taskDTO.getStatus(), taskDTO.getName(), taskDTO.getCreationDate(), taskDTO.getDescription());
    }

    public static TaskDTO toUser(final Task task) {
        if (task == null) {
            return null;
        }
        return new TaskDTO(task.getId(),task.getStatus(), task.getName(), task.getCreationDate(), task.getDescription());
    }
}
