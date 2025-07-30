package com.example.taskmanager.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class updateTaskDTO {
    String description;
    String deadline;
    Boolean completed;

}
