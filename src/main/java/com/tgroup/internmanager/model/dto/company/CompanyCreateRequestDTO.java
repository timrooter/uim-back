package com.tgroup.internmanager.model.dto.company;

import lombok.Data;

@Data
public class CompanyCreateRequestDTO {
    private String companyName;
    private String email;
    private String password;
}
