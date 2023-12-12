package ru.abolodurin.taskmanager.service;

import org.springframework.data.domain.Page;
import ru.abolodurin.taskmanager.model.dto.CommentRequest;
import ru.abolodurin.taskmanager.model.dto.CommentResponse;
import ru.abolodurin.taskmanager.model.entity.User;

public interface CommentService {
    Page<CommentResponse> create(User user, CommentRequest commentRequest);

    Page<CommentResponse> showByTaskId(Long taskId);

}
