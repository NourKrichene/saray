package com.nour.saray.infra.user.model;

import com.nour.saray.domain.model.Status;

import java.time.LocalDateTime;

public record TaskDTO (String id, Status status, String name, LocalDateTime creationDate, String description, int priority) {

}
