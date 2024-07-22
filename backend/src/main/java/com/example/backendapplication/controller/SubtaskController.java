package com.example.backendapplication.controller;

import com.example.backendapplication.model.SubtaskCreationRequest;
import com.example.backendapplication.model.Subtask;
import com.example.backendapplication.model.Task;
import com.example.backendapplication.service.ISubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks/{taskId}/subtasks")
public class SubtaskController {

    private final ISubtaskService subtaskService;

    @GetMapping
    public ResponseEntity<List<Subtask>> getSubtasks(@PathVariable UUID taskId) {
        List<Subtask> subtasks = subtaskService.getAllSubtasks(taskId);
        return ResponseEntity.ok(subtasks);
    }

    @PostMapping
    public ResponseEntity<Subtask> createSubtask(@PathVariable UUID taskId, @RequestBody @Valid SubtaskCreationRequest request) {
        Subtask subtask = new Subtask();
        subtask.setName(request.getName());
        subtask.setCompleted(request.isCompleted());

        // Fetch the task from the repository and set it to the subtask
        Task task = subtaskService.getTaskById(taskId); 
        subtask.setTask(task);

        subtask.setCreatedDate(new Date());
        subtask.setUpdatedDate(new Date());

        Subtask createdSubtask = subtaskService.createSubtask(taskId, subtask);
        return ResponseEntity.ok(createdSubtask);
    }

    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable UUID taskId, @PathVariable UUID subtaskId) {
        subtaskService.deleteSubtask(taskId, subtaskId);
        return ResponseEntity.noContent().build();
    }
}
