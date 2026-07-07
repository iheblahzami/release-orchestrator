CREATE TABLE pipeline_runs (
                               id UUID PRIMARY KEY,
                               repository VARCHAR(100),
                               branch VARCHAR(100),
                               commit_sha VARCHAR(50),
                               commit_message TEXT,
                               author VARCHAR(100),
                               status VARCHAR(30),
                               docker_image VARCHAR(255),
                               deployment_status VARCHAR(30),
                               started_at TIMESTAMP,
                               finished_at TIMESTAMP
);
