package com.tgroup.internmanager.service.impl;

import com.tgroup.internmanager.auth.*;
import com.tgroup.internmanager.config.JwtTokenProvider;
import com.tgroup.internmanager.model.dto.auth.LoginResponseDTO;
import com.tgroup.internmanager.model.entity.*;
import com.tgroup.internmanager.repository.*;
import com.tgroup.internmanager.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final StudentRepository studentRepo;
    private final UniversityRepository universityRepo;
    private final CompanyRepository companyRepo;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> registerStudent(String fullName, String email, String password) {
        if (studentRepo.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Студент с таким email уже существует");
        }

        Student student = Student.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        studentRepo.save(student);
        return ResponseEntity.ok("Студент успешно зарегистрирован");
    }

    @Override
    public ResponseEntity<?> registerUniversity(String universityName, String email, String password) {
        if (universityRepo.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Университет с таким email уже существует");
        }

        University university = University.builder()
                .universityName(universityName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        universityRepo.save(university);
        return ResponseEntity.ok("Университет успешно зарегистрирован");
    }

    @Override
    public ResponseEntity<?> registerCompany(String companyName, String email, String password) {
        if (companyRepo.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Компания с таким email уже существует");
        }

        Company company = Company.builder()
                .companyName(companyName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        companyRepo.save(company);
        return ResponseEntity.ok("Компания успешно зарегистрирована");
    }

    @Override
    public ResponseEntity<LoginResponseDTO> login(String email, String password) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Неверный логин или пароль");
        }

        UserPrincipal user = (UserPrincipal) new CustomUserDetailsService(
                studentRepo, universityRepo, companyRepo
        ).loadUserByUsername(email);

        String token = jwtProvider.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(token, user.getRole().name()));
    }
}
