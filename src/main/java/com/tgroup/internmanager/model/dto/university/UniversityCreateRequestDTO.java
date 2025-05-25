package com.tgroup.internmanager.model.dto.university;

import lombok.Data;

@Data
public class UniversityCreateRequestDTO {
    private String universityName;
    private String email;
    private String password;
}
