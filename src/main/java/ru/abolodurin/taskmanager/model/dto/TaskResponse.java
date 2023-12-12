package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.abolodurin.taskmanager.model.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public record TaskResponse(
        @Schema(description = "Task ID")
        Long id,
        @Schema(description = "Task header")
        String header,
        @Schema(description = "Task description")
        String description,
        @Schema(description = "Task timestamp")
        LocalDateTime timestamp,
        @Schema(description = "Task status")
        Task.Status status,
        @Schema(description = "Task priority")
        Task.Priority priority,
        @Schema(description = "Task author")
        String author,
        @Schema(description = "Task performer")
        String performer,
        @Schema(description = "Comments")
        List<CommentResponse> comments
) {
}
