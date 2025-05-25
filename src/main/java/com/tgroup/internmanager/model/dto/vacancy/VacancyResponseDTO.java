package com.tgroup.internmanager.model.dto.vacancy;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VacancyResponseDTO {
    private String vacancyId;
    private String title;
    private String description;
    private String requirements;
    private String location;
    private String category;
    private String workType;
    private String companyId;
    private String companyName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
