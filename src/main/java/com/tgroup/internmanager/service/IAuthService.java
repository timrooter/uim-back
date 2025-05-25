package com.tgroup.internmanager.service;

import com.tgroup.internmanager.model.dto.auth.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> registerStudent(String fullName, String email, String password);
    ResponseEntity<?> registerUniversity(String universityName, String email, String password);
    ResponseEntity<?> registerCompany(String companyName, String email, String password);
    ResponseEntity<LoginResponseDTO> login(String email, String password);

}
