package ru.abolodurin.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.abolodurin.taskmanager.model.entity.Comment;
import ru.abolodurin.taskmanager.model.entity.Task;

import java.util.Optional;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>,
        JpaRepository<Comment, Long> {
    Optional<Page<Comment>> findByTask(Task task, Pageable pageable);

}
