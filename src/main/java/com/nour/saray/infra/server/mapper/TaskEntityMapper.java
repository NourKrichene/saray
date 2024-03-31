package com.nour.saray.infra.server.mapper;

import com.nour.saray.infra.server.model.Task;

public final class TaskEntityMapper {


    private TaskEntityMapper() {
    }

    public static com.nour.saray.domain.model.Task toDomain(Task task) {
        if (task == null) {
            return null;
        }
        return new com.nour.saray.domain.model.Task(task.getId(), task.getStatus(),task.getName(), task.getCreationDate(),
                task.getDescription(), task.getPriority(),
                null);
    }

    public static Task toServer(com.nour.saray.domain.model.Task task) {
        if (task == null) {
            return null;
        }
        return new Task(task.id(),task.status(),task.name(), task.creationDate(), task.description(), task.priority(),
                null);
    }

}
