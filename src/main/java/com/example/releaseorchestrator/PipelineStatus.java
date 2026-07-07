package com.example.releaseorchestrator;

public enum PipelineStatus {
    RECEIVED,
    BUILDING,
    TESTING,
    IMAGE_PUSHED,
    DEPLOYING,
    SUCCESS,
    BUILD_FAILED,
    TEST_FAILED,
    DEPLOY_FAILED,
    ROLLED_BACK
}
