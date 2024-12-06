package io.mountblue.naukriclone.mapper;

import io.mountblue.naukriclone.dto.JobDTO;
import io.mountblue.naukriclone.entity.Employer;
import io.mountblue.naukriclone.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public JobDTO toDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setSalaryRange(job.getSalaryRange());
        jobDTO.setExperienceRequired(job.getExperienceRequired());
        jobDTO.setPostedDate(job.getPostedDate());
        jobDTO.setEmployerName(job.getEmployer() != null ? job.getEmployer().getCompanyName() : null);
        return jobDTO;
    }

    public Job toEntity(JobDTO jobDTO, Employer employer) {
        Job job = new Job();
        job.setId(jobDTO.getId());
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setSalaryRange(jobDTO.getSalaryRange());
        job.setExperienceRequired(jobDTO.getExperienceRequired());
        job.setPostedDate(jobDTO.getPostedDate());
        job.setEmployer(employer);
        return job;
    }
}