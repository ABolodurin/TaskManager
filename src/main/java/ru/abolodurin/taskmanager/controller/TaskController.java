package ru.abolodurin.taskmanager.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import ru.abolodurin.taskmanager.model.dto.ChangeStatusRequest;
import ru.abolodurin.taskmanager.model.dto.TaskRequest;
import ru.abolodurin.taskmanager.model.dto.TaskResponse;
import ru.abolodurin.taskmanager.model.dto.TaskUpdateRequest;
import ru.abolodurin.taskmanager.model.dto.UserRequest;

import java.security.Principal;

public interface TaskController {
    ResponseEntity<Page<TaskResponse>> createTask(TaskRequest taskRequest, Principal principal);

    ResponseEntity<Page<TaskResponse>> updateTask(TaskUpdateRequest taskUpdateRequest, Principal principal);

    ResponseEntity<Page<TaskResponse>> deleteTask(Long taskId, Principal principal);

    ResponseEntity<Page<TaskResponse>> changeStatus(ChangeStatusRequest changeStatusRequest, Principal principal);

    ResponseEntity<TaskResponse> show(Long taskId);

    ResponseEntity<Page<TaskResponse>> showAllByUser(UserRequest userRequest);

}
