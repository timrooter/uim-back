package com.tgroup.internmanager.model.dto.university;

import lombok.Data;
import java.util.Map;

@Data
public class UniversityStatsDTO {
    private String universityId;
    private String universityName;
    private Map<String, Long> statusCounts; // ключи: "Резюме не создано", "В поиске", "Нашёл"
}