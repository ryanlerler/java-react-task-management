package com.example.backendapplication.service;

import com.example.backendapplication.exceptions.TaskNotFoundException;
import com.example.backendapplication.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ITaskService {
    Page<Task> getAllTasks(Pageable page);
    Task createTask(String title, String description);
    Task toggleTaskCompletion(UUID taskId) throws TaskNotFoundException;
    Optional<Task> findById(UUID taskId); 
    void deleteTask(UUID taskId) throws TaskNotFoundException; 
    Task updateTask(UUID id, String title, String description) throws TaskNotFoundException;
}
