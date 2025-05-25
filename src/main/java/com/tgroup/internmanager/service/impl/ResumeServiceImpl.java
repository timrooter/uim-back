package com.tgroup.internmanager.service.impl;

import com.tgroup.internmanager.model.entity.Application;
import com.tgroup.internmanager.model.entity.Resume;
import com.tgroup.internmanager.model.entity.Student;
import com.tgroup.internmanager.repository.ApplicationRepository;
import com.tgroup.internmanager.repository.ResumeRepository;
import com.tgroup.internmanager.repository.StudentRepository;
import com.tgroup.internmanager.service.IResumeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements IResumeService {

    private final ResumeRepository resumeRepo;
    private final StudentRepository studentRepo;
    private final ApplicationRepository applicationRepo;

    @Override
    public Resume createResume(String studentId, Resume resumeData) {
        Student student = studentRepo.findById(studentId).orElseThrow();

        if (resumeRepo.existsByStudent(student)) {
            throw new IllegalStateException("У студента уже есть резюме");
        }

        Resume resume = Resume.builder()
                .student(student)
                .title(resumeData.getTitle())
                .summary(resumeData.getSummary())
                .skills(resumeData.getSkills())
                .education(resumeData.getEducation())
                .gender(resumeData.getGender())
                .phone(resumeData.getPhone())
                .socialMedia(resumeData.getSocialMedia())
                .location(resumeData.getLocation())
                .birthday(resumeData.getBirthday())
                .portfolioLink(resumeData.getPortfolioLink())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return resumeRepo.save(resume);
    }

    @Override
    public Resume getResumeByStudent(String studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        return resumeRepo.findByStudent(student)
                .orElseThrow(() -> new IllegalStateException("Резюме не найдено"));
    }

    @Override
    public void deleteResume(String studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Студент не найден: " + studentId));

        Resume resume = resumeRepo.findByStudent(student)
                .orElseThrow(() -> new IllegalStateException("Резюме не найдено для студента: " + studentId));

        // Удаляем отклики вручную через репозиторий
        applicationRepo.deleteAll(applicationRepo.findByResume(resume));
        System.out.println("Отклики удалены");

        // Удаляем резюме напрямую через SQL
        resumeRepo.deleteByResumeIdNative(resume.getResumeId());
        System.out.println("Резюме удалено через native SQL");
    }





}
