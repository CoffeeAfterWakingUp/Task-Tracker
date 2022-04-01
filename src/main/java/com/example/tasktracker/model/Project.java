package com.example.tasktracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class, stores 'project' table's properties
 *
 * @author Olzhas Syrbek
 */
@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    private Integer priority;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        task.setProject(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setProject(null);
    }

    public enum ProjectStatus {
        NOT_STARTED,
        ACTIVE,
        COMPLETED
    }

}
