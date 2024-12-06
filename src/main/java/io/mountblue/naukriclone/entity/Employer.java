package io.mountblue.naukriclone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter


@Entity
public class Employer {
    @Id
    private UUID id;

    private String companyName;
    private String industry;
    private String profilePicture;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "employer")
    private List<Job> jobPostings;
}