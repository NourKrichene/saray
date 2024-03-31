package com.nour.saray.infra.server.model;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
    public class User {
        @Id
        private String id;

        private String email;

    @CreatedDate
    private LocalDateTime creationDate;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Task> tasks = new ArrayList<>();

          public User() {
            }

            public User(String id, String email, LocalDateTime creationDate, List<Task> tasks) {
                this.id = id;
                this.email = email;
                this.creationDate = creationDate;
                this.tasks = tasks;
            }

            public String getId() {
                return id;
            }

            public String getEmail() {
                return email;
            }

            public List<Task> getTasks() {
                return tasks;
            }

            public void setTasks(List<Task> tasks) {
                this.tasks = tasks;
            }

            public void addTask(Task task) {
                tasks.add(task);
                task.setUser(this);
            }

            public LocalDateTime getCreationDate() {
                return creationDate;
            }

    public void setId(String id) {
        this.id = id;
    }
}

