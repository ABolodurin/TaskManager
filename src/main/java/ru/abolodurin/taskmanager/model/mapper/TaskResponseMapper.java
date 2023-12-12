package ru.abolodurin.taskmanager.model.mapper;

import ru.abolodurin.taskmanager.model.dto.TaskResponse;
import ru.abolodurin.taskmanager.model.entity.Task;

import java.util.function.Function;

public interface TaskResponseMapper extends Function<Task, TaskResponse> {
    TaskResponse apply(Task task);

}
