package com.example.releaseorchestrator;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pipeline_runs")
public class PipelineRun {
    @Id
    private UUID id;

    private String repository;
    private String branch;
    private String commitSha;
    private String commitMessage;
    private String author;

    @Enumerated(EnumType.STRING)
    private PipelineStatus status;

    private String dockerImage;
    private String deploymentStatus;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
}
