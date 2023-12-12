package ru.abolodurin.taskmanager.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import ru.abolodurin.taskmanager.model.dto.CommentRequest;
import ru.abolodurin.taskmanager.model.dto.CommentResponse;

import java.security.Principal;

public interface CommentController {
    ResponseEntity<Page<CommentResponse>> createComment(CommentRequest commentRequest, Principal principal);

    ResponseEntity<Page<CommentResponse>> showComments(Long taskId);

}
