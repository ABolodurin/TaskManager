package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
        @Schema(description = "Task ID")
        @Min(value = 1L)
        @NotNull(message = "Task ID must be not empty")
        Long taskID,
        @Schema(description = "Comment message")
        @NotEmpty(message = "Message must be not empty")
        String message
) {
}
