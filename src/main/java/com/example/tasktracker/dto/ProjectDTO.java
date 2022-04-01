package com.example.tasktracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO class for Project entity
 *
 * @author Olzhas Syrbek
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProjectDTO {
    private Long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime completionDate;
    private Integer priority;
    private String status;

    private List<TaskDTO> tasks;
}
