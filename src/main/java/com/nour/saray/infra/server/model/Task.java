package com.nour.saray.infra.server.model;

import com.nour.saray.domain.model.Status;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Status status;

    @CreatedDate
    private LocalDateTime creationDate;

    private String description;

    private int priority;

    public Task() {
    }

    public Task(String id, Status status, String name, LocalDateTime creationDate, String description, int priority) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}