package com.example.releaseorchestrator.dto;

import com.example.releaseorchestrator.PipelineStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for updating pipeline status")
public class StatusUpdateDto {

    @Schema(description = "New pipeline status", example = "DEPLOYING")
    private PipelineStatus status;

    public PipelineStatus getStatus() { return status; }
    public void setStatus(PipelineStatus status) { this.status = status; }
}