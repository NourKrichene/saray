package com.nour.saray.domain.model;

import java.time.LocalDateTime;


public record Task (String id,Status status, String name, LocalDateTime creationDate, String description, int priority) {

}