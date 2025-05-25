package com.tgroup.internmanager.service;

import com.tgroup.internmanager.model.entity.Vacancy;

import java.util.List;

public interface IVacancyService {
    List<Vacancy> getAllVacancies();
    List<Vacancy> searchVacanciesByTitle(String title);
    List<Vacancy> searchVacanciesByCategory(String category);
    Vacancy getVacancyById(String vacancyId);
}
