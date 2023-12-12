package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.abolodurin.taskmanager.model.entity.Task;

public record ChangeStatusRequest(
        @Schema(description = "Task id")
        @Min(value = 1L)
        @NotNull(message = "Field taskId must be not empty")
        Long taskId,
        @Schema(description = "Task status")
        @NotNull(message = "Field status must be not empty")
        Task.Status status
) {
}
