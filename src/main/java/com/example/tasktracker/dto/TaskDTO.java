package com.example.tasktracker.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO class for Task entity
 *
 * @author Olzhas Syrbek
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private Integer priority;
    private String status;
    private String projectName;

}
