package com.example.tasktracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity class, stores 'task' table's properties
 * @author Olzhas Syrbek
 */
@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;


    private Integer priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    public Task(Long id, String name, String description, Integer priority, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public enum TaskStatus {
        TO_DO,
        IN_PROGRESS,
        DONE
    }
}
