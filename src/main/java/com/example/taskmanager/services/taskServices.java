package com.example.taskmanager.services;

import com.example.taskmanager.entities.taskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class taskServices {
    private static ArrayList<taskEntity> tasks = new ArrayList<>();
    private static int taskid = 1;

    public static taskEntity addTask(String title, String description, String deadline) {
        taskEntity task = new taskEntity();
        task.setId(taskid++);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setCompleted(false);
        tasks.add(task);
        return task;
    }

    public ArrayList<taskEntity> getTasks() {
        return tasks;
    }

    public static taskEntity getTaskById(int id) {
        for (taskEntity task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}
