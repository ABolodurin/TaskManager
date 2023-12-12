package ru.abolodurin.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.abolodurin.taskmanager.model.entity.Task;
import ru.abolodurin.taskmanager.model.entity.User;

import java.util.Optional;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>,
        JpaRepository<Task, Long> {
    Optional<Page<Task>> findAllByAuthor(User user, Pageable pageable);

}
