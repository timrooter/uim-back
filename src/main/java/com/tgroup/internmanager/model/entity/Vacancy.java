package com.tgroup.internmanager.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vacancies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String vacancyId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String title;
    private String companyName;
    private String description;
    private String requirements;
    private String location;
    private String category;
    private String workType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;
}
