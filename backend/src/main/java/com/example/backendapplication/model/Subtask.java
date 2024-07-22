package com.example.backendapplication.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subtasks")  
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private UUID id;

    @Column(nullable = false)
    private String name;

    private boolean completed;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}