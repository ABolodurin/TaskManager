package ru.abolodurin.taskmanager.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static CommentBuilder builder() {
        return new CommentBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", taskID=" + task.getId() +
                ", user=" + user.getUsername() +
                '}';
    }

    public static class CommentBuilder {
        private Long id;
        private String message;
        private LocalDateTime timestamp;
        private Task task;
        private User user;

        CommentBuilder() {
        }

        public CommentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CommentBuilder message(String message) {
            this.message = message;
            return this;
        }

        public CommentBuilder task(Task task) {
            this.task = task;
            return this;
        }

        public CommentBuilder user(User user) {
            this.user = user;
            return this;
        }

        public Comment build() {
            timestamp = LocalDateTime.now();
            return new Comment(id, message, timestamp, task, user);
        }

        public String toString() {
            return "Comment.CommentBuilder(id=" + this.id +
                    ", message=" + this.message +
                    ", timestamp=" + this.timestamp +
                    ", taskID=" + this.task.getId() +
                    ", user=" + this.user + ")";
        }

    }

}
