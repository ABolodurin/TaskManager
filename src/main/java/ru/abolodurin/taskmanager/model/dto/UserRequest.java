package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record UserRequest(
        @Schema(description = "username")
        @NotEmpty(message = "Field username must be not empty")
        String username
) {
}
