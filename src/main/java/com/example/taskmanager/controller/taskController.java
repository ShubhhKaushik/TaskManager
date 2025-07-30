package com.example.taskmanager.controller;


import com.example.taskmanager.dtos.createTaskDTO;
import com.example.taskmanager.dtos.errorResponseDTO;
import com.example.taskmanager.dtos.updateTaskDTO;
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

    @PatchMapping("/{id}")
    public ResponseEntity<taskEntity> updateTask(@PathVariable("id") Integer id,@RequestBody updateTaskDTO body) throws ParseException {
        var task = taskServices.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<errorResponseDTO> handleErrors(Exception e) {
        if (e instanceof ParseException) {
        return ResponseEntity.badRequest().body(new errorResponseDTO("Invalid Date Format"));
    }
        e.printStackTrace();
        return  ResponseEntity.internalServerError().body(new errorResponseDTO("Invalid Server Error"));
    }
}
