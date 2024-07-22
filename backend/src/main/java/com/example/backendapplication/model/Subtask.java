package com.example.backendapplication.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")

public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private boolean completed;

    private Date createdDate;

    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
