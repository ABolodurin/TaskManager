package ru.abolodurin.taskmanager.model.mapper;

import org.springframework.stereotype.Component;
import ru.abolodurin.taskmanager.model.dto.CommentResponse;
import ru.abolodurin.taskmanager.model.entity.Comment;

import java.util.function.Function;

@Component
public class CommentResponseMapper implements Function<Comment, CommentResponse> {
    @Override
    public CommentResponse apply(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getTask().getId(),
                comment.getMessage(),
                comment.getTimestamp(),
                comment.getUser().getUsername()
        );
    }
}
