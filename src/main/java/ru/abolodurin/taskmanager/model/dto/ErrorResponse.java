package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorResponse(@Schema(description = "Error message") String message) {
}
