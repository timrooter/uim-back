package com.tgroup.internmanager.controller;

import com.tgroup.internmanager.auth.UserPrincipal;
import com.tgroup.internmanager.mapper.ApplicationMapper;
import com.tgroup.internmanager.mapper.CompanyMapper;
import com.tgroup.internmanager.mapper.VacancyMapper;
import com.tgroup.internmanager.model.dto.application.*;
import com.tgroup.internmanager.model.dto.company.CompanyResponseDTO;
import com.tgroup.internmanager.model.dto.vacancy.*;
import com.tgroup.internmanager.model.entity.Application;
import com.tgroup.internmanager.model.entity.Vacancy;
import com.tgroup.internmanager.service.ICompanyService;
import com.tgroup.internmanager.service.impl.ApplicationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService companyService;
    private final VacancyMapper vacancyMapper;
    private final ApplicationMapper applicationMapper;
    private final CompanyMapper companyMapper;
    private final ApplicationServiceImpl applicationService;

    @PostMapping("/{companyId}/vacancies")
    public VacancyResponseDTO createVacancy(@PathVariable String companyId, @RequestBody VacancyCreateRequestDTO dto) {
        Vacancy vacancy = vacancyMapper.toEntity(dto);
        Vacancy saved = companyService.createVacancy(companyId, vacancy);
        return vacancyMapper.toResponseDTO(saved);
    }

//    @DeleteMapping("/{companyId}/vacancies/{vacancyId}")
//    public void deleteVacancy(@PathVariable String companyId, @PathVariable String vacancyId) {
//        companyService.deleteVacancy(companyId, vacancyId);
//    }

    @DeleteMapping("/vacancies/{vacancyId}")
    public void deleteVacancy(@PathVariable String vacancyId, @AuthenticationPrincipal UserPrincipal principal) {
        companyService.deleteVacancy(principal.getId(), vacancyId);
    }

    @GetMapping("/{companyId}/vacancies")
    public List<VacancyResponseDTO> getMyVacancies(@PathVariable String companyId) {
        return companyService.getMyVacancies(companyId)
                .stream().map(vacancyMapper::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/{companyId}/vacancies/{vacancyId}")
    public VacancyResponseDTO getVacancy(@PathVariable String companyId, @PathVariable String vacancyId) {
        return vacancyMapper.toResponseDTO(companyService.getVacancyDetails(companyId, vacancyId));
    }

    @GetMapping("/{companyId}/applications")
    public List<ApplicationResponseDTO> getAllApplications(@PathVariable String companyId) {
        return companyService.getApplicationsByCompany(companyId)
                .stream().map(applicationMapper::toResponseDTO).collect(Collectors.toList());
    }

//    @GetMapping("/applications/{applicationId}")
//    public ApplicationResponseDTO getApplication(@PathVariable String applicationId) {
//        Application application = companyService.getApplicationDetails(applicationId);
//        return applicationMapper.toResponseDTO(application);
//    }

    @GetMapping("/application/{applicationId}")
    public ApplicationResponseDTO getApplicationById(@PathVariable String applicationId) {
        Application app = applicationService.getApplicationById(applicationId);
        return applicationMapper.toResponseDTO(app);
    }


    @PostMapping("/applications/{applicationId}/accept")
    public void acceptApplication(
            @PathVariable String applicationId,
            @RequestBody ApplicationFeedbackDTO feedbackDTO
    ) {
        companyService.acceptApplication(applicationId, feedbackDTO.getFeedback());
    }

    @PostMapping("/applications/{applicationId}/reject")
    public void rejectApplication(
            @PathVariable String applicationId,
            @RequestBody ApplicationFeedbackDTO feedbackDTO
    ) {
        companyService.rejectApplication(applicationId, feedbackDTO.getFeedback());
    }

    @GetMapping("/me")
    public CompanyResponseDTO getCompanyInfo(@AuthenticationPrincipal UserPrincipal user) {
        return companyMapper.toResponseDTO(companyService.getByEmail(user.getUsername()));
    }

    @GetMapping("/applications")
    public List<ApplicationResponseDTO> getCompanyApplications(@AuthenticationPrincipal UserPrincipal user) {
        return applicationService.getApplicationsByCompany(user.getId())
                .stream()
                .map(applicationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


}
