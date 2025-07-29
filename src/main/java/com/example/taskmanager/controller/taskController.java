package com.example.taskmanager.controller;


import com.example.taskmanager.dtos.createTaskDTO;
import com.example.taskmanager.entities.taskEntity;
import com.example.taskmanager.services.taskServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class taskController {
    private final taskServices TaskServices;

    public taskController(taskServices taskServices) {
        TaskServices = taskServices;
    }

    @GetMapping
    public ResponseEntity<List<taskEntity>> getAllTasks() {
        var tasks = TaskServices.getTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping
    ResponseEntity<taskEntity> getTaskById(@PathVariable("id") Integer id) {
        var task = taskServices.getTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<taskEntity> addTask(@RequestBody createTaskDTO body) throws ParseException {
        var task = taskServices.addTask(body.getTitle(), body.getDescription(), body.getDeadline());

        return ResponseEntity.ok(task);
    }
}
