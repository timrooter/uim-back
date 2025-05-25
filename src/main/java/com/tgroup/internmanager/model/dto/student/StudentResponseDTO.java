package com.tgroup.internmanager.model.dto.student;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private String studentId;
    private String fullName;
    private String email;
    private String universityName;
}
