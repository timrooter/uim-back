package com.tgroup.internmanager.service.impl;

import com.tgroup.internmanager.model.entity.Vacancy;
import com.tgroup.internmanager.repository.VacancyRepository;
import com.tgroup.internmanager.service.IVacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements IVacancyService {

    private final VacancyRepository vacancyRepo;

    @Override
    public List<Vacancy> getAllVacancies() {
        return vacancyRepo.findAll();
    }

    @Override
    public List<Vacancy> searchVacanciesByTitle(String title) {
        return vacancyRepo.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Vacancy> searchVacanciesByCategory(String category) {
        return vacancyRepo.findByCategory(category);
    }

    @Override
    public Vacancy getVacancyById(String vacancyId) {
        return vacancyRepo.findById(vacancyId).orElseThrow(() ->
                new IllegalArgumentException("Вакансия не найдена: " + vacancyId));
    }
}
