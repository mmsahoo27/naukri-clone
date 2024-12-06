package io.mountblue.naukriclone.entity;

import io.mountblue.naukriclone.entity.enums.JobSeekerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class JobSeeker {
    @Id
    private UUID id;

    private String name;

    private String headline;

    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private JobSeekerType jobSeekerType;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "job_seeker_skills",
            joinColumns = @JoinColumn(name = "job_seeker_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    @ManyToMany
    @JoinTable(
            name = "job_seeker_languages",
            joinColumns = @JoinColumn(name = "job_seeker_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;

    private String resume;
}