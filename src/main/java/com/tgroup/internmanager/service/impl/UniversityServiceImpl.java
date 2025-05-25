package com.tgroup.internmanager.service.impl;

import com.tgroup.internmanager.model.entity.*;
import com.tgroup.internmanager.repository.*;
import com.tgroup.internmanager.service.IUniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements IUniversityService {

    private final UniversityRepository universityRepo;
    private final StudentRepository studentRepo;
    private final ResumeRepository resumeRepo;
    private final ApplicationRepository applicationRepo;

    @Override
    public List<Student> getStudentsByUniversity(String universityId) {
        University university = universityRepo.findById(universityId).orElseThrow();
        return studentRepo.findAllByUniversity(university);
    }

    @Override
    public long countStudentsByUniversity(String universityId) {
        University university = universityRepo.findById(universityId).orElseThrow();
        return studentRepo.countByUniversity(university);
    }

    @Override
    public Map<String, Long> getStudentStatusStats(String universityId) {
        University university = universityRepo.findById(universityId).orElseThrow();
        List<Student> students = studentRepo.findAllByUniversity(university);

        Map<String, Long> statusMap = new HashMap<>();
        statusMap.put("NO_RESUME", 0L);
        statusMap.put("HAS_RESUME", 0L);
        statusMap.put("HAS_ACCEPTED", 0L);

        for (Student student : students) {
            boolean hasResume = resumeRepo.existsByStudent(student);

            if (!hasResume) {
                statusMap.computeIfPresent("NO_RESUME", (k, v) -> v + 1);
                continue;
            }

            long acceptedCount = applicationRepo.countByStudentAndStatus(student, ApplicationStatus.ACCEPTED);
            if (acceptedCount > 0) {
                statusMap.computeIfPresent("HAS_ACCEPTED", (k, v) -> v + 1);
            } else {
                statusMap.computeIfPresent("HAS_RESUME", (k, v) -> v + 1);
            }
        }

        return statusMap;
    }


    @Override
    public University getByEmail(String email) {
        return universityRepo.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("Университет не найден"));
    }

    public List<University> getAll() {
        return universityRepo.findAll();
    }

}
