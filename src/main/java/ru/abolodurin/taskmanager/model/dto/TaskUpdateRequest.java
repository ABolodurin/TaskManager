package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.abolodurin.taskmanager.model.entity.Task;

public record TaskUpdateRequest(
        @Schema(description = "Task id")
        @Min(value = 1L)
        @NotNull(message = "Field id must be not empty")
        Long id,
        @Schema(description = "Task header")
        String header,
        @Schema(description = "Task description")
        String description,
        @Schema(description = "Task status")
        Task.Status status,
        @Schema(description = "Task priority")
        Task.Priority priority,
        @Schema(description = "Task performer's username")
        String performerUsername
) {
}
