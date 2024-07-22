package com.example.backendapplication.repository;

import com.example.backendapplication.model.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, UUID> {
    List<Subtask> findByTaskId(UUID taskId);
}