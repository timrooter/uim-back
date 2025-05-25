package com.tgroup.internmanager.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String applicationId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Resume resume;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private String feedback;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
