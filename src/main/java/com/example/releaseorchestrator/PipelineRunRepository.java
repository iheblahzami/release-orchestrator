package com.example.releaseorchestrator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PipelineRunRepository extends JpaRepository<PipelineRun, UUID> {}
