package com.tgroup.internmanager.model.dto.application;

import com.tgroup.internmanager.model.dto.resume.ResumeResponseDTO;
import com.tgroup.internmanager.model.dto.vacancy.VacancyResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationResponseDTO {
    private String applicationId;
    private String studentId;
    private String status;      // PENDING, ACCEPTED, REJECTED
    private String feedback;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ResumeResponseDTO resume;
    private VacancyResponseDTO vacancy;
}
