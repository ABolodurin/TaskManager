package ru.abolodurin.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.abolodurin.taskmanager.configuration.SwaggerConfig;
import ru.abolodurin.taskmanager.model.dto.CommentRequest;
import ru.abolodurin.taskmanager.model.dto.CommentResponse;
import ru.abolodurin.taskmanager.model.entity.User;
import ru.abolodurin.taskmanager.service.CommentService;
import ru.abolodurin.taskmanager.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Tag(name = SwaggerConfig.COMMENTS_TAG)
public class CommentControllerImpl implements CommentController {
    private final CommentService commentService;
    private final UserService userService;

    @Override
    @PostMapping
    @Operation(summary = "Create comment",
            responses = @ApiResponse(responseCode = "200", description = "Successful creation"))
    public ResponseEntity<Page<CommentResponse>> createComment(
            @Valid @RequestBody CommentRequest commentRequest, @Parameter(hidden = true) Principal principal) {
        User user = userService.findByUsername(principal.getName());

        return ResponseEntity.ok(commentService.create(user, commentRequest));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Shows comments by one post",
            responses = @ApiResponse(responseCode = "200", description = "Successful creation"))
    public ResponseEntity<Page<CommentResponse>> showComments(
            @PathVariable(value = "id") Long taskId) {
        return ResponseEntity.ok(commentService.showByTaskId(taskId));
    }

}
