package com.tgroup.internmanager.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "resumes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String resumeId;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private String title;
    private String summary;
    private String skills;
    private String education;
    private String gender;
    private String phone;
    private String socialMedia;
    private String location;
    private String birthday;
    private String portfolioLink;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;
}
