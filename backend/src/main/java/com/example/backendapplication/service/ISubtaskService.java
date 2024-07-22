package com.example.backendapplication.service;

import com.example.backendapplication.exceptions.TaskNotFoundException;
import com.example.backendapplication.model.Subtask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISubtaskService {
    Page<Subtask> getAllSubtasks(Pageable page);
    Subtask createSubtask(UUID taskId, Subtask subtask);
    void deleteSubtask(UUID taskId, UUID subtaskId);
}
