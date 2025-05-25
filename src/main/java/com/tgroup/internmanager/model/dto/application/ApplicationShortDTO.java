package com.tgroup.internmanager.model.dto.application;

import lombok.Data;

@Data
public class ApplicationShortDTO {
    private String applicationId;
    private String studentId;
    private String vacancyId;
    private String status;
    private String feedback;
}
