package com.tgroup.internmanager.model.dto.vacancy;

import lombok.Data;

@Data
public class VacancyCreateRequestDTO {
    private String title;
    private String description;
    private String requirements;
    private String location;
    private String category;
    private String workType; // "Офлайн", "Онлайн", "Гибрид"
}
