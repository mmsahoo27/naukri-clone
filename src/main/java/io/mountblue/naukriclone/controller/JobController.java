package io.mountblue.naukriclone.controller;

import io.mountblue.naukriclone.dto.JobDTO;
import io.mountblue.naukriclone.entity.Job;
import io.mountblue.naukriclone.mapper.JobMapper;
import io.mountblue.naukriclone.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    private final JobMapper jobMapper;

    @Autowired
    public JobController(JobService jobService, JobMapper jobMapper) {
        this.jobService = jobService;
        this.jobMapper = jobMapper;
    }

    @GetMapping
    public String getAllJobsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<JobDTO> jobPage = jobService.getAllJobs(page, size).map(jobMapper::toDTO);
        model.addAttribute("jobs", jobPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", jobPage.getTotalPages());
        return "Jobs";
    }

    @GetMapping("/{id}")
    public String getJobById(@PathVariable UUID id, Model model) {
        JobDTO job = jobMapper.toDTO(jobService.getJobById(id));
        model.addAttribute("job", job);
        return "JobDetails";
    }

    @GetMapping("/create")
    public String showCreateJobForm(Model model) {
        model.addAttribute("jobDTO", new JobDTO());
        return "CreateJob";
    }

    @PostMapping("/create")
    public String createJob(@ModelAttribute JobDTO jobDTO) {
        Job job = jobMapper.toEntity(jobDTO, null); // Pass null for employer in creation if needed
        jobService.createJob(job);
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String showEditJobForm(@PathVariable UUID id, Model model) {
        JobDTO job = jobMapper.toDTO(jobService.getJobById(id));
        model.addAttribute("job", job);
        return "CreateJob";
    }

    @PostMapping("/edit/{id}")
    public String updateJob(@PathVariable UUID id, @ModelAttribute JobDTO jobDTO) {
        jobDTO.setId(id);
        jobService.createJob(jobMapper.toEntity(jobDTO, null)); // Null employer for now
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable UUID id) {
        jobService.deleteJobById(id);
        return "redirect:/jobs";
    }

    @GetMapping("/search")
    public String searchJobsPaginated(@RequestParam String location, @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size, Model model) {
        Page<JobDTO> jobPage = jobService.findJobsByLocation(location, page, size).map(jobMapper::toDTO);
        model.addAttribute("jobs", jobPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", jobPage.getTotalPages());
        model.addAttribute("location", location);
        return "Jobs";
    }
}