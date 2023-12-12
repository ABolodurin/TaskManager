package ru.abolodurin.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.abolodurin.taskmanager.configuration.SwaggerConfig;
import ru.abolodurin.taskmanager.model.dto.ChangeStatusRequest;
import ru.abolodurin.taskmanager.model.dto.TaskRequest;
import ru.abolodurin.taskmanager.model.dto.TaskResponse;
import ru.abolodurin.taskmanager.model.dto.TaskUpdateRequest;
import ru.abolodurin.taskmanager.model.dto.UserRequest;
import ru.abolodurin.taskmanager.model.entity.User;
import ru.abolodurin.taskmanager.service.TaskService;
import ru.abolodurin.taskmanager.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = SwaggerConfig.TASKS_TAG)
public class TaskControllerImpl implements TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @Override
    @PostMapping("/create")
    @Operation(summary = "Create task",
            responses = @ApiResponse(responseCode = "200", description = "Successful creation"))
    public @ResponseBody ResponseEntity<Page<TaskResponse>> createTask(
            @Valid @RequestBody TaskRequest taskRequest, @Parameter(hidden = true) Principal principal) {
        User user = userService.findByUsername(principal.getName());

        return ResponseEntity.ok(taskService.create(user, taskRequest));
    }

    @Override
    @PutMapping("/update")
    @Operation(summary = "Update task",
            responses = @ApiResponse(responseCode = "200", description = "Successful update"))
    public ResponseEntity<Page<TaskResponse>> updateTask(
            @Valid @RequestBody TaskUpdateRequest taskUpdateRequest, @Parameter(hidden = true) Principal principal) {
        User user = userService.findByUsername(principal.getName());

        return ResponseEntity.ok(taskService.updateTask(user, taskUpdateRequest));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete task",
            responses = @ApiResponse(responseCode = "200", description = "Successful delete"))
    public ResponseEntity<Page<TaskResponse>> deleteTask(
            @PathVariable(value = "id") Long taskId, @Parameter(hidden = true) Principal principal) {
        User user = userService.findByUsername(principal.getName());

        return ResponseEntity.ok(taskService.deleteTask(user, taskId));
    }

    @Override
    @PutMapping("/status")
    @Operation(summary = "Change status",
            responses = @ApiResponse(responseCode = "200", description = "Successful update"))
    public ResponseEntity<Page<TaskResponse>> changeStatus(
            @Valid @RequestBody ChangeStatusRequest changeStatusRequest, @Parameter(hidden = true) Principal principal) {
        User user = userService.findByUsername(principal.getName());

        return ResponseEntity.ok(taskService.changeStatus(user, changeStatusRequest));
    }

    @Override
    @PostMapping("/show")
    @Operation(summary = "Show all tasks by user",
            responses = @ApiResponse(responseCode = "200", description = "Successful"))
    public ResponseEntity<Page<TaskResponse>> showAllByUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(taskService.showAllByUser(userRequest));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Show task by Id",
            responses = @ApiResponse(responseCode = "200", description = "Successful"))
    public ResponseEntity<TaskResponse> show(
            @PathVariable(value = "id") Long taskId) {
        return ResponseEntity.ok(taskService.show(taskId));
    }

}
