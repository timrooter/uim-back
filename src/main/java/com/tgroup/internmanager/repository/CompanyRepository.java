package com.tgroup.internmanager.repository;

import com.tgroup.internmanager.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findByEmail(String email);
    boolean existsByEmail(String email);
}
