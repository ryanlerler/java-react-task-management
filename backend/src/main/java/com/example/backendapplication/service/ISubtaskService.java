package com.example.backendapplication.service;

import com.example.backendapplication.model.Subtask;
import com.example.backendapplication.model.Task;

import java.util.List;
import java.util.UUID;

public interface ISubtaskService {
    List<Subtask> getAllSubtasks(UUID taskId);
    Subtask createSubtask(UUID taskId, Subtask subtask);
    void deleteSubtask(UUID taskId, UUID subtaskId);
    Task getTaskById(UUID taskId);
}
