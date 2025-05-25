package com.tgroup.internmanager.service;

import com.tgroup.internmanager.model.entity.Application;

import java.util.List;

public interface IApplicationService {
    Application submitApplication(String studentId, String vacancyId);
    List<Application> getApplicationsByStudent(String studentId);
    List<Application> getApplicationsByVacancy(String vacancyId);
    Application getApplicationById(String applicationId);
    long countAcceptedApplications(String studentId);
    List<Application> getApplicationsByCompany(String companyId);

}
