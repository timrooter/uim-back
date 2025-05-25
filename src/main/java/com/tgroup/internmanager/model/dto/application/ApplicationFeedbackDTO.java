package com.tgroup.internmanager.model.dto.application;

import lombok.Data;

@Data
public class ApplicationFeedbackDTO {
    private String applicationId;
    private String status;   // "ACCEPTED" или "REJECTED"
    private String feedback;
}
