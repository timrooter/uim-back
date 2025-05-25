package com.tgroup.internmanager.controller;

import com.tgroup.internmanager.auth.UserPrincipal;
import com.tgroup.internmanager.mapper.ApplicationMapper;
import com.tgroup.internmanager.mapper.ResumeMapper;
import com.tgroup.internmanager.mapper.StudentMapper;
import com.tgroup.internmanager.mapper.VacancyMapper;
import com.tgroup.internmanager.model.dto.application.ApplicationResponseDTO;
import com.tgroup.internmanager.model.dto.resume.ResumeCreateRequestDTO;
import com.tgroup.internmanager.model.dto.resume.ResumeResponseDTO;
import com.tgroup.internmanager.model.dto.student.StudentResponseDTO;
import com.tgroup.internmanager.model.dto.vacancy.VacancyResponseDTO;
import com.tgroup.internmanager.model.entity.Resume;
import com.tgroup.internmanager.service.IResumeService;
import com.tgroup.internmanager.service.IStudentService;
import com.tgroup.internmanager.service.IApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;
    private final IApplicationService applicationService;
    private final VacancyMapper vacancyMapper;
    private final ApplicationMapper applicationMapper;
    private final IResumeService resumeService;
    private final ResumeMapper resumeMapper;
    private final StudentMapper studentMapper;

    @GetMapping("/vacancies")
    public List<VacancyResponseDTO> getAllVacancies() {
        return studentService.getAllVacancies()
                .stream().map(vacancyMapper::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/vacancies/search")
    public List<VacancyResponseDTO> searchVacancies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String category) {

        if (title != null) {
            return studentService.searchVacanciesByTitle(title)
                    .stream().map(vacancyMapper::toResponseDTO).collect(Collectors.toList());
        }

        if (category != null) {
            return studentService.searchVacanciesByCategory(category)
                    .stream().map(vacancyMapper::toResponseDTO).collect(Collectors.toList());
        }

        return studentService.getAllVacancies()
                .stream().map(vacancyMapper::toResponseDTO).collect(Collectors.toList());
    }

    @PostMapping("/university/{universityId}")
    public void assignUniversity(@RequestParam String studentId, @PathVariable String universityId) {
        studentService.assignUniversity(studentId, universityId);
    }

    @GetMapping("/status")
    public String getStudentStatus(@RequestParam String studentId) {
        return studentService.getStudentStatus(studentId);
    }

    @GetMapping("/applications")
    public List<ApplicationResponseDTO> getMyApplications(@AuthenticationPrincipal UserPrincipal user) {
        return studentService.getMyApplications(user.getId())
                .stream()
                .map(applicationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/vacancy/{vacancyId}/apply")
    public void applyToVacancy(
            @PathVariable String vacancyId,
            @AuthenticationPrincipal UserPrincipal user
    ) {
        applicationService.submitApplication(user.getId(), vacancyId);
    }


    @PostMapping("/resume")
    public ResumeResponseDTO createResume(
            @AuthenticationPrincipal UserPrincipal user,
            @RequestBody ResumeCreateRequestDTO dto
    ) {
        Resume saved = resumeService.createResume(user.getId(), resumeMapper.toEntity(dto));
        return resumeMapper.toResponseDTO(saved);
    }


    @DeleteMapping("/resume")
    public void deleteResume(@AuthenticationPrincipal UserPrincipal user) {
        resumeService.deleteResume(user.getId());
        System.out.println("Удаляем резюме у " + user.getId());
    }

    @DeleteMapping("/resume/me")
    public ResponseEntity<Void> deleteMyResume(@AuthenticationPrincipal UserPrincipal user) {
        resumeService.deleteResume(user.getId());
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/resume")
//    public ResumeResponseDTO getResume(@RequestParam String studentId) {
//        Resume resume = resumeService.getResumeByStudent(studentId);
//        return resumeMapper.toResponseDTO(resume);
//    }

    @GetMapping("/resume")
    public ResumeResponseDTO getResume(@AuthenticationPrincipal UserPrincipal user) {
        Resume resume = resumeService.getResumeByStudent(user.getId());
        return resumeMapper.toResponseDTO(resume);
    }


    @GetMapping("/me")
    public StudentResponseDTO getStudentInfo(@AuthenticationPrincipal UserPrincipal user) {
        return studentMapper.toResponseDTO(studentService.getByEmail(user.getUsername()));
    }
}
