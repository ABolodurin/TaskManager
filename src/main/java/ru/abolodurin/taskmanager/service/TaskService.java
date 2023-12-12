package ru.abolodurin.taskmanager.service;

import org.springframework.data.domain.Page;
import ru.abolodurin.taskmanager.model.dto.ChangeStatusRequest;
import ru.abolodurin.taskmanager.model.dto.TaskRequest;
import ru.abolodurin.taskmanager.model.dto.TaskResponse;
import ru.abolodurin.taskmanager.model.dto.TaskUpdateRequest;
import ru.abolodurin.taskmanager.model.dto.UserRequest;
import ru.abolodurin.taskmanager.model.entity.Task;
import ru.abolodurin.taskmanager.model.entity.User;

public interface TaskService {
    Page<TaskResponse> create(User user, TaskRequest taskRequest);

    Page<TaskResponse> updateTask(User user, TaskUpdateRequest taskUpdateRequest);

    Page<TaskResponse> deleteTask(User user, Long taskId);

    Page<TaskResponse> changeStatus(User user, ChangeStatusRequest changeStatusRequest);

    TaskResponse show(Long taskId);

    Task findById(Long taskId);

    Page<TaskResponse> showAllByUser(UserRequest userRequest);

    Page<TaskResponse> showAll();

}
