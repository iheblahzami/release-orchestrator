package com.example.releaseorchestrator.services;

import com.example.releaseorchestrator.PipelineRun;
import com.example.releaseorchestrator.dto.PipelineRunDto;
import com.example.releaseorchestrator.PipelineRunRepository;
import com.example.releaseorchestrator.PipelineStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PipelineService {

    private final PipelineRunRepository pipelineRunRepository;

    public PipelineService(PipelineRunRepository pipelineRunRepository) {
        this.pipelineRunRepository = pipelineRunRepository;
    }

    public List<PipelineRunDto> getAll() {
        return pipelineRunRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PipelineRunDto getById(UUID id) {
        PipelineRun run = pipelineRunRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pipeline run not found"));
        return toDto(run);
    }

    public void updateStatus(UUID id, PipelineStatus status) {
        PipelineRun run = pipelineRunRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pipeline run not found"));
        run.setStatus(status);
        pipelineRunRepository.save(run);
    }

    private PipelineRunDto toDto(PipelineRun run) {
        PipelineRunDto dto = new PipelineRunDto();
        dto.setId(run.getId());
        dto.setRepository(run.getRepository());
        dto.setBranch(run.getBranch());
        dto.setCommitSha(run.getCommitSha());
        dto.setStatus(run.getStatus());
        dto.setDockerImage(run.getDockerImage());
        dto.setDeploymentStatus(run.getDeploymentStatus());
        dto.setStartedAt(run.getStartedAt());
        dto.setFinishedAt(run.getFinishedAt());
        return dto;
    }
}