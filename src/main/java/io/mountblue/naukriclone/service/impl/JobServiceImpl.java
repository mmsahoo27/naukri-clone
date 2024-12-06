package io.mountblue.naukriclone.service.impl;

import io.mountblue.naukriclone.entity.Job;
import io.mountblue.naukriclone.repository.JobRepository;
import io.mountblue.naukriclone.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Page<Job> getAllJobs(int page, int size) {
        return jobRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(UUID id) {
        return jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @Override
    public Page<Job> getJobsByEmployerId(UUID employerId, int page, int size) {
        return jobRepository.findByEmployerId(employerId, PageRequest.of(page, size));
    }

    @Override
    public void deleteJobById(UUID id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Page<Job> findJobsByLocation(String location, int page, int size) {
        return jobRepository.findByLocationContainingIgnoreCase(location, PageRequest.of(page, size));
    }
}