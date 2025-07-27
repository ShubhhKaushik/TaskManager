package com.example.taskmanager.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class createTaskDTO {
    String title;
    String description;
    String deadline;
}
