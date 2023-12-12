package ru.abolodurin.taskmanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.abolodurin.taskmanager.model.entity.Task;

@Data
public class TaskRequest {
    @Schema(description = "Task header")
    @NotEmpty(message = "Field header must be not empty")
    String header;
    @Schema(description = "Task description")
    @NotEmpty(message = "Field description must be not empty")
    String description;
    @Schema(description = "Task status")
    Task.Status status;
    @Schema(description = "Task priority")
    Task.Priority priority;
    @Schema(description = "Task performer")
    String performer;
}
