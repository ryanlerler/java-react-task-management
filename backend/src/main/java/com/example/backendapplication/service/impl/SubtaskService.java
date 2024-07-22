package com.example.backendapplication.service.impl;

import com.example.backendapplication.exceptions.TaskNotFoundException;
import com.example.backendapplication.model.Subtask;
import com.example.backendapplication.model.Task;
import com.example.backendapplication.repository.SubtaskRepository;
import com.example.backendapplication.repository.TaskRepository;
import com.example.backendapplication.service.ISubtaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SubtaskService implements ISubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<Subtask> getAllSubtasks(UUID taskId) {
        Task task = getTaskById(taskId);
        return subtaskRepository.findByTaskId(task.getId());
    }

    @Override
    public Subtask createSubtask(UUID taskId, Subtask subtask) {
        Task task = getTaskById(taskId);
        subtask.setTask(task);
        subtask.setCreatedDate(new Date());
        subtask.setUpdatedDate(new Date());
        return subtaskRepository.save(subtask);
    }

    @Override
    public void deleteSubtask(UUID taskId, UUID subtaskId) {
        Subtask subtask = subtaskRepository.findById(subtaskId)
                .orElseThrow(() ->  new TaskNotFoundException(subtaskId));
        if (!subtask.getTask().getId().equals(taskId)) {
            throw  new TaskNotFoundException(taskId);
        }
        subtaskRepository.delete(subtask);
    }

    @Override
    public Task getTaskById(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() ->  new TaskNotFoundException(taskId));
    }
}