package ru.abolodurin.taskmanager.model.mapper;

import org.springframework.stereotype.Component;
import ru.abolodurin.taskmanager.model.dto.TaskResponse;
import ru.abolodurin.taskmanager.model.entity.Task;

import java.util.Collections;

@Component("taskResponseSimpleMapper")
public class TaskResponseWithoutCommentsMapper implements TaskResponseMapper {

    @Override
    public TaskResponse apply(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getHeader(),
                task.getDescription(),
                task.getTimestamp(),
                task.getStatus(),
                task.getPriority(),
                task.getAuthor().getUsername(),
                task.getPerformer() == null ? null : task.getPerformer().getUsername(),
                Collections.emptyList());
    }

}
