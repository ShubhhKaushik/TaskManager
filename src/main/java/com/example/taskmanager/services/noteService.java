package com.example.taskmanager.services;

import com.example.taskmanager.entities.noteEntities;
import com.example.taskmanager.entities.taskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class noteService {
    private final taskServices TaskService;
    private final HashMap<Integer, TaskNotesHolder> taskNoteHolders = new HashMap<>();

    public NoteService(taskServices taskService) {
        this.taskService = taskService;
    }

    private static class TaskNotesHolder {
        private int noteId = 0;
        private final List<noteEntities> notes = new ArrayList<>();
    }

    public List<noteEntities> getNoteForTask(int taskId) {
        if (taskService.getTaskById(taskId) == null) return null;
        return taskNoteHolders
                .computeIfAbsent(taskId, k -> new TaskNotesHolder())
                .notes;
    }

    public noteEntities addNoteForTask(int taskId, String title, String body) {
        taskEntity task = taskService.getTaskById(taskId);
        if (task == null) return null;

        TaskNotesHolder holder = taskNoteHolders.computeIfAbsent(taskId, k -> new TaskNotesHolder());

        noteEntities note = new noteEntities();
        note.setId(holder.noteId++);
        note.setTitle(title);
        note.setBody(body);
        holder.notes.add(note);
        return note;
    }
}
