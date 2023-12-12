package ru.abolodurin.taskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.abolodurin.taskmanager.model.dto.ChangeStatusRequest;
import ru.abolodurin.taskmanager.model.dto.TaskRequest;
import ru.abolodurin.taskmanager.model.dto.TaskResponse;
import ru.abolodurin.taskmanager.model.dto.TaskUpdateRequest;
import ru.abolodurin.taskmanager.model.dto.UserRequest;
import ru.abolodurin.taskmanager.model.entity.CommonException;
import ru.abolodurin.taskmanager.model.entity.Task;
import ru.abolodurin.taskmanager.model.entity.User;
import ru.abolodurin.taskmanager.model.mapper.TaskResponseMapper;
import ru.abolodurin.taskmanager.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    public static final Pageable DEFAULT_PAGEABLE = PageRequest.of(
            0, 10, Sort.by("status", "priority").ascending()
                    .and(Sort.by("timestamp").descending()));

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskResponseMapper taskResponseSimpleMapper;
    private final TaskResponseMapper taskResponseFullMapper;

    @Override
    public Page<TaskResponse> create(User user, TaskRequest taskRequest) {
        User performer = taskRequest.getPerformer() == null ? null : userService.findByUsername(taskRequest.getPerformer());

        if (taskRequest.getPriority() == null) taskRequest.setPriority(Task.Priority.MEDIUM);

        if (taskRequest.getStatus() == null) {
            Task.Status status =
                    taskRequest.getPerformer() == null ? Task.Status.CREATED : Task.Status.IN_PROGRESS;
            taskRequest.setStatus(status);
        }

        taskRepository.save(Task
                .builder()
                .header(taskRequest.getHeader())
                .description(taskRequest.getDescription())
                .status(taskRequest.getStatus())
                .priority(taskRequest.getPriority())
                .author(user)
                .performer(performer)
                .build());

        return showAll();
    }

    @Override
    public Page<TaskResponse> updateTask(User user, TaskUpdateRequest taskUpdateRequest) {
        Task task = findById(taskUpdateRequest.id());
        checkTaskOwner(task, user);

        if (taskUpdateRequest.header() != null) task.setHeader(taskUpdateRequest.header());
        if (taskUpdateRequest.description() != null) task.setDescription(taskUpdateRequest.description());
        if (taskUpdateRequest.status() != null) task.setStatus(taskUpdateRequest.status());
        if (taskUpdateRequest.priority() != null) task.setPriority(taskUpdateRequest.priority());

        if (taskUpdateRequest.performerUsername() != null) {
            User performer = userService.findByUsername(taskUpdateRequest.performerUsername());
            task.setPerformer(performer);
        }

        taskRepository.save(task);

        return showAll();
    }

    @Override
    public Page<TaskResponse> deleteTask(User user, Long taskId) {
        Task task = findById(taskId);
        checkTaskOwner(task, user);

        taskRepository.deleteById(taskId);

        return showAll();
    }

    @Override
    public Page<TaskResponse> changeStatus(User user, ChangeStatusRequest changeStatusRequest) {
        Task task = findById(changeStatusRequest.taskId());

        if (user.equals(task.getAuthor()) || user.equals(task.getPerformer())) task.setStatus(
                changeStatusRequest.status());
        taskRepository.save(task);

        return showAll();
    }


    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new CommonException("Task not found. Task ID: " + id));
    }

    @Override
    public Page<TaskResponse> showAllByUser(UserRequest userRequest) {
        User user = userService.findByUsername(userRequest.username());

        return taskRepository.findAllByAuthor(user, DEFAULT_PAGEABLE)
                .orElseThrow(() -> new CommonException("Tasks not found by user " + user.getUsername()))
                .map(taskResponseFullMapper);
    }

    @Override
    public Page<TaskResponse> showAll() {
        return taskRepository.findAll(DEFAULT_PAGEABLE)
                .map(taskResponseSimpleMapper);
    }

    @Override
    public TaskResponse show(Long taskId) {
        return taskResponseFullMapper.apply(findById(taskId));
    }

    private void checkTaskOwner(Task task, User user) {
        if (!task.getAuthor().equals(user)) throw new CommonException(
                user.getUsername() + " is not the owner of this task. Task ID: " + task.getId());
    }

}
