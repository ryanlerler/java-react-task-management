package com.example.backendapplication.controller;

import com.example.backendapplication.exceptions.TaskNotFoundException;
import com.example.backendapplication.model.Task;
import com.example.backendapplication.model.TaskCreationRequest;
import com.example.backendapplication.service.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Tasks", description = "Task management")
@RequestMapping("/tasks")
@RequiredArgsConstructor
@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class TaskController {

    private final ITaskService taskService;

    @Operation(summary = "Get all tasks")
    @GetMapping("/")
    public ResponseEntity<Page<Task>> getTasks(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                               @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                               @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        log.info("Getting all tasks with page {}, size {}, orderBy {}, direction {}", page, size, orderBy, direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), orderBy));

        return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }

    @Operation(summary = "Create a task")
    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody @Valid TaskCreationRequest request) {
        log.info("Creating task with name {} and details {}", request.getName(), request.getDetails());
        return ResponseEntity.ok(taskService.createTask(request.getName(), request.getDetails()));
    }

    @Operation(summary = "Toggle Task Completion")
    @PutMapping("/task/{id}")
    public ResponseEntity<Task> toggleTaskCompletion(@PathVariable UUID id) throws TaskNotFoundException {
        log.info("Toggling task completion for task with ID {}", id);
        return ResponseEntity.ok(taskService.toggleTaskCompletion(id));
    }

    @Operation(summary = "Get a task by ID")
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID id) throws TaskNotFoundException {
        // TODO: Implement this method for Task 1a.
        throw new RuntimeException("Not implemented");
    }

    @Operation(summary = "Delete a task")
    @DeleteMapping("/task/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable UUID id) throws TaskNotFoundException {
        // TODO: Implement this method for Task 1b.
        throw new RuntimeException("Not implemented");
    }

    @Operation(summary = "Update a task")
    @PostMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody @Valid TaskCreationRequest request) throws TaskNotFoundException {
        // TODO: Implement this method for Task 1c.
        throw new RuntimeException("Not implemented");
    }
}
