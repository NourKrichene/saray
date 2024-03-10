package com.nour.saray.domain.model;

import java.time.LocalDateTime;


public class Task {

    private String id;

    private String name;

    private Status status;

    private LocalDateTime creationDate;

    private String description;



    public Task(String id,Status status, String name, LocalDateTime creationDate, String description) {
        this.id=id;
        this.status = status;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
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

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
