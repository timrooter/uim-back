package com.tgroup.internmanager.repository;

import com.tgroup.internmanager.model.entity.Application;
import com.tgroup.internmanager.model.entity.Resume;
import com.tgroup.internmanager.model.entity.Student;
import com.tgroup.internmanager.model.entity.Vacancy;
import com.tgroup.internmanager.model.entity.ApplicationStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, String> {
    List<Application> findByStudent(Student student);
    List<Application> findByVacancy(Vacancy vacancy);
    List<Application> findByResume(Resume resume);
    long countByStudentAndStatus(Student student, ApplicationStatus status);
    List<Application> findByVacancyIn(List<Vacancy> vacancies);
}
