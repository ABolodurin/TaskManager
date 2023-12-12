package ru.abolodurin.taskmanager.model.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.abolodurin.taskmanager.model.dto.CommentResponse;
import ru.abolodurin.taskmanager.model.dto.TaskResponse;
import ru.abolodurin.taskmanager.model.entity.Task;

import java.util.ArrayList;
import java.util.List;

@Component("taskResponseFullMapper")
@AllArgsConstructor
public class TaskResponseWithCommentsMapper implements TaskResponseMapper {
    private final CommentResponseMapper commentResponseMapper;

    @Override
    public TaskResponse apply(Task task) {
        List<CommentResponse> comments = new ArrayList<>();
        task.getComments().forEach(c -> comments.add(commentResponseMapper.apply(c)));

        return new TaskResponse(
                task.getId(),
                task.getHeader(),
                task.getDescription(),
                task.getTimestamp(),
                task.getStatus(),
                task.getPriority(),
                task.getAuthor().getUsername(),
                task.getPerformer() == null ? null : task.getPerformer().getUsername(),
                comments);
    }

}
