package ru.abolodurin.taskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.abolodurin.taskmanager.model.dto.CommentRequest;
import ru.abolodurin.taskmanager.model.dto.CommentResponse;
import ru.abolodurin.taskmanager.model.entity.Comment;
import ru.abolodurin.taskmanager.model.entity.CommonException;
import ru.abolodurin.taskmanager.model.entity.Task;
import ru.abolodurin.taskmanager.model.entity.User;
import ru.abolodurin.taskmanager.model.mapper.CommentResponseMapper;
import ru.abolodurin.taskmanager.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    public static final Pageable DEFAULT_PAGEABLE = PageRequest.of(
            0, 10, Sort.by("timestamp").descending());

    public final TaskService taskService;
    private final CommentRepository commentRepository;
    private final CommentResponseMapper commentResponseMapper;

    @Override
    public Page<CommentResponse> create(User user, CommentRequest commentRequest) {
        commentRepository.save(Comment
                .builder()
                .task(taskService.findById(commentRequest.taskID()))
                .message(commentRequest.message())
                .user(user)
                .build());

        return showByTaskId(commentRequest.taskID());
    }

    @Override
    public Page<CommentResponse> showByTaskId(Long taskId) {
        Task task = taskService.findById(taskId);

        return commentRepository
                .findByTask(task, DEFAULT_PAGEABLE)
                .orElseThrow(() -> new CommonException("Comments not found"))
                .map(commentResponseMapper);
    }

}
