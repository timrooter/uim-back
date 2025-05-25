package com.tgroup.internmanager.service.impl;

import com.tgroup.internmanager.model.entity.*;
import com.tgroup.internmanager.repository.*;
import com.tgroup.internmanager.service.ICompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepo;
    private final VacancyRepository vacancyRepo;
    private final ApplicationRepository applicationRepo;

    @Override
    public Vacancy createVacancy(String companyId, Vacancy vacancy) {
        Company company = companyRepo.findById(companyId).orElseThrow();

        vacancy.setCompany(company);
        vacancy.setCompanyName(company.getCompanyName());
        vacancy.setCreatedAt(LocalDateTime.now());
        vacancy.setUpdatedAt(LocalDateTime.now());

        return vacancyRepo.save(vacancy);
    }

    @Override
    @Transactional
    public void deleteVacancy(String companyId, String vacancyId) {
        Vacancy vacancy = vacancyRepo.findById(vacancyId)
                .orElseThrow(() -> new IllegalArgumentException("Vacancy not found"));

        // Безопасность — только владелец может удалить
        if (!vacancy.getCompany().getCompanyId().equals(companyId)) {
            throw new SecurityException("You are not allowed to delete this vacancy");
        }

        // Удаление всех откликов на эту вакансию
        List<Application> applications = applicationRepo.findByVacancy(vacancy);
        applicationRepo.deleteAll(applications);

        // Удаление вакансии
        vacancyRepo.delete(vacancy);
    }


    @Override
    public List<Vacancy> getMyVacancies(String companyId) {
        Company company = companyRepo.findById(companyId).orElseThrow();
        return vacancyRepo.findAllByCompany(company);
    }

    @Override
    public Vacancy getVacancyDetails(String companyId, String vacancyId) {
        Vacancy vacancy = vacancyRepo.findById(vacancyId).orElseThrow();
        if (!vacancy.getCompany().getCompanyId().equals(companyId)) {
            throw new SecurityException("Нет доступа к этой вакансии");
        }
        return vacancy;
    }

    @Override
    public List<Application> getApplicationsByCompany(String companyId) {
        Company company = companyRepo.findById(companyId).orElseThrow();
        List<Vacancy> vacancies = vacancyRepo.findAllByCompany(company);

        return vacancies.stream()
                .flatMap(vacancy -> applicationRepo.findByVacancy(vacancy).stream())
                .collect(Collectors.toList());
    }

    @Override
    public Application getApplicationDetails(String applicationId) {
        return applicationRepo.findById(applicationId).orElseThrow();
    }

    @Override
    public void acceptApplication(String applicationId, String feedback) {
        Application application = applicationRepo.findById(applicationId).orElseThrow();
        application.setStatus(ApplicationStatus.ACCEPTED);
        application.setFeedback(feedback);
        application.setUpdatedAt(LocalDateTime.now());
        applicationRepo.save(application);
    }

    @Override
    public void rejectApplication(String applicationId, String feedback) {
        Application application = applicationRepo.findById(applicationId).orElseThrow();
        application.setStatus(ApplicationStatus.REJECTED);
        application.setFeedback(feedback);
        application.setUpdatedAt(LocalDateTime.now());
        applicationRepo.save(application);
    }

    @Override
    public Company getByEmail(String email) {
        return companyRepo.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("Компания не найдена"));
    }

}
