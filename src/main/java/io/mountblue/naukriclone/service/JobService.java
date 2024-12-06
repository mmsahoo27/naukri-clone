package io.mountblue.naukriclone.service;

import io.mountblue.naukriclone.entity.Job;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface JobService {
    public Page<Job> getAllJobs(int page, int size);
    public void createJob(Job job);
    public Job getJobById(UUID id);
    public Page<Job> getJobsByEmployerId(UUID ID, int page, int size);
    public void deleteJobById(UUID id);
    public Page<Job> findJobsByLocation(String location, int page, int size);
}
