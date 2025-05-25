package com.tgroup.internmanager.model.dto.vacancy;

import lombok.Data;

@Data
public class VacancyShortDTO {
    private String vacancyId;
    private String title;
    private String category;
    private String location;
    private String companyName;
}
