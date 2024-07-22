package com.example.backendapplication.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubtaskCreationRequest {

    @NotBlank(message = "Subtask name is required")
    private String name;

    private boolean completed;

    @NotNull(message = "Task ID is required")
    private UUID taskId;
}