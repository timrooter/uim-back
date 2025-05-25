package com.tgroup.internmanager.model.dto.student;

import lombok.Data;

@Data
public class StudentCreateRequestDTO {
    private String fullName;
    private String email;
    private String password;
}
