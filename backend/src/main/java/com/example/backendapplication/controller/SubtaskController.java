package com.example.backendapplication.controller;

import com.example.backendapplication.model.Subtask;
import com.example.backendapplication.service.ISubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks/{taskId}/subtasks")
public class SubtaskController {

    @Autowired
    private ISubtaskService subtaskService;

    @GetMapping
    public ResponseEntity<List<Subtask>> getSubtasks(@PathVariable UUID taskId) {
        List<Subtask> subtasks = subtaskService.getAllSubtasks();
        return ResponseEntity.ok(subtasks);
    }

    @PostMapping
    public ResponseEntity<Subtask> createSubtask(@PathVariable UUID taskId, @RequestBody Subtask subtask) {
        Subtask createdSubtask = subtaskService.createSubtask(taskId, subtask);
        return ResponseEntity.ok(createdSubtask);
    }

    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable UUID taskId, @PathVariable UUID subtaskId) {
        subtaskService.deleteSubtask(taskId, subtaskId);
        return ResponseEntity.noContent().build();
    }
}
