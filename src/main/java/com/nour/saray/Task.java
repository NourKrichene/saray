package com.nour.saray;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String name;

    private Status status;

    private LocalDateTime creationDate;

    private String description;

    public Task() {
    }

    public Task(Status status, String name, LocalDateTime creationDate, String description) {
        this.status = status;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}