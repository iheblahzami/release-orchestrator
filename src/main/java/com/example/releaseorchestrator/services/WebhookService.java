package com.example.releaseorchestrator.services;

import com.example.releaseorchestrator.PipelineRun;
import com.example.releaseorchestrator.PipelineRunRepository;
import com.example.releaseorchestrator.PipelineStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class WebhookService {

    private final PipelineRunRepository pipelineRunRepository;
    private final ObjectMapper objectMapper;

    @Value("${github.webhook.secret}")
    private String githubSecret;

    public WebhookService(PipelineRunRepository pipelineRunRepository, ObjectMapper objectMapper) {
        this.pipelineRunRepository = pipelineRunRepository;
        this.objectMapper = objectMapper;
    }

    public void handleWebhook(String signature, String payload) {
        if (!verifySignature(signature, payload)) {
            throw new SecurityException("Invalid GitHub signature");
        }

        try {
            JsonNode root = objectMapper.readTree(payload);

            String repository = root.path("repository").path("name").asText();
            String ref = root.path("ref").asText();
            String commitSha = root.path("after").asText();

            PipelineRun run = new PipelineRun();
            run.setId(UUID.randomUUID());
            run.setRepository(repository);
            run.setBranch(ref.replace("refs/heads/", ""));
            run.setCommitSha(commitSha);
            run.setStatus(PipelineStatus.RECEIVED);
            run.setStartedAt(java.time.LocalDateTime.now());

            pipelineRunRepository.save(run);

            // Optionally trigger GitHub Actions workflow dispatch here
            // githubService.triggerWorkflow(repository, run.getBranch());

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse webhook payload", e);
        }
    }

    private boolean verifySignature(String signature, String payload) {
        try {
            String expectedPrefix = "sha256=";
            if (!signature.startsWith(expectedPrefix)) {
                return false;
            }

            String expected = signature.substring(expectedPrefix.length());

            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(githubSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmac.init(keySpec);

            byte[] hash = hmac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String actual = bytesToHex(hash);

            return actual.equals(expected);
        } catch (Exception e) {
            return false;
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}