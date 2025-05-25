package com.tgroup.internmanager.controller;

import com.tgroup.internmanager.auth.UserPrincipal;
import com.tgroup.internmanager.mapper.StudentMapper;
import com.tgroup.internmanager.mapper.UniversityMapper;
import com.tgroup.internmanager.model.dto.student.StudentResponseDTO;
import com.tgroup.internmanager.model.dto.university.UniversityResponseDTO;
import com.tgroup.internmanager.model.dto.university.UniversityStatsDTO;
import com.tgroup.internmanager.model.entity.Student;
import com.tgroup.internmanager.service.IUniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/university")
@RequiredArgsConstructor
public class UniversityController {

    private final IUniversityService universityService;
    private final StudentMapper studentMapper;
    private final UniversityMapper universityMapper;

    @GetMapping("/{universityId}/students")
    public List<StudentResponseDTO> getStudents(@PathVariable String universityId) {
        List<Student> students = universityService.getStudentsByUniversity(universityId);
        return students.stream().map(studentMapper::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/{universityId}/students/count")
    public long getStudentCount(@PathVariable String universityId) {
        return universityService.countStudentsByUniversity(universityId);
    }

    @GetMapping("/{universityId}/students/status-stats")
    public UniversityStatsDTO getStatusStats(@PathVariable String universityId) {
        Map<String, Long> stats = universityService.getStudentStatusStats(universityId);
        String universityName = universityService.getStudentsByUniversity(universityId)
                .stream().findFirst()
                .map(s -> s.getUniversity().getUniversityName())
                .orElse("Неизвестно");

        UniversityStatsDTO dto = new UniversityStatsDTO();
        dto.setUniversityId(universityId);
        dto.setUniversityName(universityName);
        dto.setStatusCounts(stats);
        return dto;
    }

    @GetMapping("/me")
    public UniversityResponseDTO getUniversityInfo(@AuthenticationPrincipal UserPrincipal user) {
        return universityMapper.toResponseDTO(universityService.getByEmail(user.getUsername()));
    }



}
