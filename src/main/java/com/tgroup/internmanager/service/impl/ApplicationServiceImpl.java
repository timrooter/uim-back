package com.tgroup.internmanager.service.impl;

import com.tgroup.internmanager.model.entity.*;
import com.tgroup.internmanager.repository.*;
import com.tgroup.internmanager.service.IApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements IApplicationService {

    private final ApplicationRepository applicationRepo;
    private final StudentRepository studentRepo;
    private final VacancyRepository vacancyRepo;
    private final ResumeRepository resumeRepo;
    private final CompanyRepository companyRepo;

    @Override
    public Application submitApplication(String studentId, String vacancyId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        Vacancy vacancy = vacancyRepo.findById(vacancyId).orElseThrow();
        Resume resume = resumeRepo.findByStudent(student)
                .orElseThrow(() -> new IllegalStateException("Резюме отсутствует"));

        boolean alreadyApplied = applicationRepo.findByStudent(student)
                .stream()
                .anyMatch(app -> app.getVacancy().getVacancyId().equals(vacancyId));

        if (alreadyApplied) {
            throw new IllegalStateException("Вы уже откликались на эту вакансию");
        }

        Application application = Application.builder()
                .student(student)
                .vacancy(vacancy)
                .resume(resume)
                .status(ApplicationStatus.PENDING)
                .feedback(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return applicationRepo.save(application);
    }

    @Override
    public List<Application> getApplicationsByStudent(String studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        return applicationRepo.findByStudent(student);
    }

    @Override
    public List<Application> getApplicationsByVacancy(String vacancyId) {
        Vacancy vacancy = vacancyRepo.findById(vacancyId).orElseThrow();
        return applicationRepo.findByVacancy(vacancy);
    }

    @Override
    public Application getApplicationById(String applicationId) {
        return applicationRepo.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Отклик не найден"));
    }

    @Override
    public long countAcceptedApplications(String studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        return applicationRepo.countByStudentAndStatus(student, ApplicationStatus.ACCEPTED);
    }

    @Override
    public List<Application> getApplicationsByCompany(String companyId) {
        Company company = companyRepo.findById(companyId).orElseThrow();
        List<Vacancy> vacancies = vacancyRepo.findByCompany(company);
        return applicationRepo.findByVacancyIn(vacancies);
    }

}
