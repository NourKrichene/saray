package com.nour.saray.domain.model;

import java.time.LocalDateTime;
import java.util.List;


public record User(String id, String email, LocalDateTime creationDate, List<Task> tasks) {
}