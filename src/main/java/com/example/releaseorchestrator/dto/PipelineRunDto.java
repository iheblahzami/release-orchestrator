package com.example.releaseorchestrator.dto;

import com.example.releaseorchestrator.PipelineStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;
import java.time.LocalDateTime;


@Schema(description = "Pipeline run details")
public class PipelineRunDto {

    @Schema(description = "Unique identifier of the pipeline run")
    private UUID id;

    @Schema(description = "Repository name")
    private String repository;

    @Schema(description = "Branch name")
    private String branch;

    @Schema(description = "Commit SHA")
    private String commitSha;

    @Schema(description = "Pipeline status", example = "SUCCESS")
    private PipelineStatus status;

    @Schema(description = "Docker image used")
    private String dockerImage;

    @Schema(description = "Deployment status", example = "DEPLOYING")
    private String deploymentStatus;

    @Schema(description = "Start time of the run")
    private LocalDateTime startedAt;

    @Schema(description = "Finish time of the run")
    private LocalDateTime finishedAt;


    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getRepository() { return repository; }
    public void setRepository(String repository) { this.repository = repository; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getCommitSha() { return commitSha; }
    public void setCommitSha(String commitSha) { this.commitSha = commitSha; }

    public PipelineStatus getStatus() { return status; }
    public void setStatus(PipelineStatus status) { this.status = status; }

    public String getDockerImage() { return dockerImage; }
    public void setDockerImage(String dockerImage) { this.dockerImage = dockerImage; }

    public String getDeploymentStatus() { return deploymentStatus; }
    public void setDeploymentStatus(String deploymentStatus) { this.deploymentStatus = deploymentStatus; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getFinishedAt() { return finishedAt; }
    public void setFinishedAt(LocalDateTime finishedAt) { this.finishedAt = finishedAt; }

}