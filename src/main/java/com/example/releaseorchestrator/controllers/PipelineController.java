package com.example.releaseorchestrator.controllers;



import com.example.releaseorchestrator.dto.PipelineRunDto;
import com.example.releaseorchestrator.dto.StatusUpdateDto;
import com.example.releaseorchestrator.services.PipelineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pipelines")
@Tag(name = "Pipeline Management", description = "APIs for managing pipeline runs")
public class PipelineController {

    private final PipelineService pipelineService;

    public PipelineController(PipelineService pipelineService) {
        this.pipelineService = pipelineService;
    }

    @GetMapping
    @Operation(summary = "List all pipeline runs", description = "Returns all pipeline runs stored in the database")
    public List<PipelineRunDto> listAll() {
        return pipelineService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get pipeline run details", description = "Returns details of a specific pipeline run by ID")
    public PipelineRunDto getById(@PathVariable UUID id) {
        return pipelineService.getById(id);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update pipeline status", description = "Updates the status of a pipeline run")
    public void updateStatus(@PathVariable UUID id, @RequestBody StatusUpdateDto dto) {
        pipelineService.updateStatus(id, dto.getStatus());
    }
}
