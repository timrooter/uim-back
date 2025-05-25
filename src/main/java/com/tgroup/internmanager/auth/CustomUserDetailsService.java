package com.tgroup.internmanager.auth;

import com.tgroup.internmanager.model.entity.*;
import com.tgroup.internmanager.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepo;
    private final UniversityRepository universityRepo;
    private final CompanyRepository companyRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (studentRepo.existsByEmail(email)) {
            Student student = studentRepo.findByEmail(email).orElseThrow();
            return UserPrincipal.builder()
                    .id(student.getStudentId())
                    .email(student.getEmail())
                    .password(student.getPassword())
                    .role(Role.ROLE_STUDENT)
                    .build();
        } else if (universityRepo.existsByEmail(email)) {
            University u = universityRepo.findByEmail(email).orElseThrow();
            return UserPrincipal.builder()
                    .id(u.getUniversityId())
                    .email(u.getEmail())
                    .password(u.getPassword())
                    .role(Role.ROLE_UNIVERSITY)
                    .build();
        } else if (companyRepo.existsByEmail(email)) {
            Company c = companyRepo.findByEmail(email).orElseThrow();
            return UserPrincipal.builder()
                    .id(c.getCompanyId())
                    .email(c.getEmail())
                    .password(c.getPassword())
                    .role(Role.ROLE_COMPANY)
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
