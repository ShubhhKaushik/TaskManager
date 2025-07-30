package com.example.taskmanager.services;

import com.example.taskmanager.entities.taskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class taskServices {
    private static ArrayList<taskEntity> tasks = new ArrayList<>();
    private static int taskid = 1;
    private static SimpleDateFormat deadLineFormatter = new SimpleDateFormat("YYYY-MM-dd");

    public static taskEntity addTask(String title, String description, String deadline) throws ParseException {
        taskEntity task = new taskEntity();
        task.setId(taskid++);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadLineFormatter.parse(deadline));
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

    public static taskEntity updateTask(int id, String description, String deadline , Boolean completed) throws ParseException {
        taskEntity task = getTaskById(id);
        if (task == null){
            return null;
        }
        if(description != null){
            task.setDescription(description);
        }
        if(deadline != null){
            task.setDeadline(deadLineFormatter.parse(deadline));
        }
        if(completed != null){
            task.setCompleted(completed);
        }
        return task;
    }
}
