package com.tgroup.internmanager.service;

import com.tgroup.internmanager.model.entity.Application;
import com.tgroup.internmanager.model.entity.Student;
import com.tgroup.internmanager.model.entity.Vacancy;

import java.util.List;

public interface IStudentService {
    void assignUniversity(String studentId, String universityId);
    String getStudentStatus(String studentId);
    List<Vacancy> getAllVacancies();
    List<Vacancy> searchVacanciesByTitle(String title);
    List<Vacancy> searchVacanciesByCategory(String category);
    List<Application> getMyApplications(String studentId);
    void applyToVacancy(String studentId, String vacancyId);
    Student getByEmail(String email);
}
