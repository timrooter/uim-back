package com.tgroup.internmanager.service.impl;

import com.tgroup.internmanager.model.entity.*;
import com.tgroup.internmanager.repository.*;
import com.tgroup.internmanager.service.IStudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepo;
    private final UniversityRepository universityRepo;
    private final ResumeRepository resumeRepo;
    private final VacancyRepository vacancyRepo;
    private final ApplicationRepository applicationRepo;

    @Override
    public void assignUniversity(String studentId, String universityId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        University university = universityRepo.findById(universityId).orElseThrow();
        student.setUniversity(university);
        studentRepo.save(student);
    }

    @Override
    public String getStudentStatus(String studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        boolean hasResume = resumeRepo.existsByStudent(student);
        if (!hasResume) return "NO_RESUME";

        long acceptedCount = applicationRepo.countByStudentAndStatus(student, ApplicationStatus.ACCEPTED);
        if (acceptedCount > 0) return "HAS_ACCEPTED";
        return "HAS_RESUME";
    }

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
    public List<Application> getMyApplications(String studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        return applicationRepo.findByStudent(student);
    }

    @Transactional
    @Override
    public void applyToVacancy(String studentId, String vacancyId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        Vacancy vacancy = vacancyRepo.findById(vacancyId).orElseThrow();
        Resume resume = resumeRepo.findByStudent(student)
                .orElseThrow(() -> new IllegalStateException("Сначала создайте резюме"));

        boolean alreadyApplied = applicationRepo.findByStudent(student)
                .stream()
                .anyMatch(app -> app.getVacancy().getVacancyId().equals(vacancyId));

        if (alreadyApplied) {
            throw new IllegalStateException("Вы уже откликались на эту вакансию");
        }

        Application app = Application.builder()
                .student(student)
                .resume(resume)
                .vacancy(vacancy)
                .status(ApplicationStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        applicationRepo.save(app);
    }
    @Override
    public Student getByEmail(String email) {
        return studentRepo.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("Студент не найден"));
    }

}
