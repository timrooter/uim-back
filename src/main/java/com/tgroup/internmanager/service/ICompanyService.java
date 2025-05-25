package com.tgroup.internmanager.service;

import com.tgroup.internmanager.model.entity.Application;
import com.tgroup.internmanager.model.entity.Company;
import com.tgroup.internmanager.model.entity.Vacancy;

import java.util.List;

public interface ICompanyService {
    Vacancy createVacancy(String companyId, Vacancy vacancy);
    void deleteVacancy(String companyId, String vacancyId);
    List<Vacancy> getMyVacancies(String companyId);
    Vacancy getVacancyDetails(String companyId, String vacancyId);
    List<Application> getApplicationsByCompany(String companyId);
    Application getApplicationDetails(String applicationId);
    void acceptApplication(String applicationId, String feedback);
    void rejectApplication(String applicationId, String feedback);

    Company getByEmail(String email);

}
