package com.example.backendapplication.service.impl;

import com.example.backendapplication.exceptions.TaskNotFoundException;
import com.example.backendapplication.model.Subtask;
import com.example.backendapplication.model.Task;
import com.example.backendapplication.repository.SubtaskRepository;
import com.example.backendapplication.repository.TaskRepository;
import com.example.backendapplication.service.ISubtaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubtaskService implements ISubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TaskRepository taskRepository;

    @Override
    public Page<Subtask> getAllSubtasks(Pageable page) {
        return subtaskRepository.findAll(page);
    }

    @Override
    public Subtask createSubtask(UUID taskId, Subtask subtask) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        subtask.setTask(task);
        subtask.setCreatedDate(new Date());
        subtask.setUpdatedDate(new Date());
        return subtaskRepository.save(subtask);
    }

    @Override
    public void deleteSubtask(UUID taskId, UUID subtaskId) {
        Subtask subtask = subtaskRepository.findById(subtaskId).orElseThrow(() -> new TaskNotFoundException(subtaskId));
        if (!subtask.getTask().getId().equals(taskId)) {
            throw new TaskNotFoundException(subtaskId);
        }
        subtaskRepository.delete(subtask);
    }
}
