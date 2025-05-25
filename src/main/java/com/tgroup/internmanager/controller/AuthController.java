package com.tgroup.internmanager.controller;

import com.tgroup.internmanager.model.dto.auth.LoginRequestDTO;
import com.tgroup.internmanager.model.dto.auth.LoginResponseDTO;
import com.tgroup.internmanager.model.dto.student.StudentCreateRequestDTO;
import com.tgroup.internmanager.model.dto.university.UniversityCreateRequestDTO;
import com.tgroup.internmanager.model.dto.company.CompanyCreateRequestDTO;
import com.tgroup.internmanager.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/student/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentCreateRequestDTO dto) {
        return authService.registerStudent(dto.getFullName(), dto.getEmail(), dto.getPassword());
    }

    @PostMapping("/university/register")
    public ResponseEntity<?> registerUniversity(@RequestBody UniversityCreateRequestDTO dto) {
        return authService.registerUniversity(dto.getUniversityName(), dto.getEmail(), dto.getPassword());
    }

    @PostMapping("/company/register")
    public ResponseEntity<?> registerCompany(@RequestBody CompanyCreateRequestDTO dto) {
        return authService.registerCompany(dto.getCompanyName(), dto.getEmail(), dto.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return authService.login(dto.getEmail(), dto.getPassword());
    }
}
