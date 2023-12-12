package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CommentResponse(
        @Schema(description = "Comment ID")
        Long id,
        @Schema(description = "Task ID")
        Long taskId,
        @Schema(description = "Comment message")
        String message,
        @Schema(description = "Comment timestamp")
        LocalDateTime timestamp,
        @Schema(description = "Comment author")
        String user
) {
}
