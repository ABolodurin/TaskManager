package ru.abolodurin.taskmanager.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "header")
    private String header;
    @Column(name = "description")
    private String description;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    private User performer;
    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    @OneToMany(mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<Comment> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", priority=" + priority +
                ", author=" + author +
                ", performer=" + performer +
                '}';
    }

    public enum Status {
        CREATED,
        IN_PROGRESS,
        DONE
    }

    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    @ToString
    public static class TaskBuilder {
        private Long id;
        private String header;
        private String description;
        private LocalDateTime timestamp;
        private Status status;
        private Priority priority;
        private User author;
        private User performer;
        private Set<Comment> comments;

        public TaskBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TaskBuilder header(String header) {
            this.header = header;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public TaskBuilder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public TaskBuilder author(User author) {
            this.author = author;
            return this;
        }

        public TaskBuilder performer(User performer) {
            this.performer = performer;
            return this;
        }

        public TaskBuilder comments(Set<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public Task build() {
            if (priority == null) priority = Priority.MEDIUM;
            if (status == null) status = Status.CREATED;
            timestamp = LocalDateTime.now();
            return new Task(id, header, description, timestamp, status, priority, author, performer, comments);
        }

    }

}
