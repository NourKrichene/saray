package com.nour.saray.infra.user.model;

import com.nour.saray.domain.model.Status;

import java.time.LocalDateTime;

public class TaskDTO {

    private String id;


    private String name;

    private Status status;

    private LocalDateTime creationDate;

    private String description;

    private int priority;

    public TaskDTO() {
    }

    public TaskDTO(String id,Status status, String name, LocalDateTime creationDate, String description, int priority) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

}
