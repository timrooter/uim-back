package com.tgroup.internmanager.repository;

import com.tgroup.internmanager.model.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, String> {
    Optional<University> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<University> findByUniversityName(String universityName);
}
