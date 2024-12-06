package io.mountblue.naukriclone.entity;

import io.mountblue.naukriclone.entity.enums.Proficiency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Proficiency proficiency;

    @ManyToMany(mappedBy = "skills")
    private List<JobSeeker> jobSeekers;
}