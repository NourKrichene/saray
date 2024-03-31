package com.nour.saray.infra.user.model;

import com.nour.saray.domain.model.Status;

import java.time.LocalDateTime;
import java.util.List;

public record UserDTO (String id,  String email, LocalDateTime creationDate, List<TaskDTO> tasks) {
}