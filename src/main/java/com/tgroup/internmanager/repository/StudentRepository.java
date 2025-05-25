package com.tgroup.internmanager.repository;

import com.tgroup.internmanager.model.entity.Student;
import com.tgroup.internmanager.model.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);

    List<Student> findAllByUniversity(University university);
    long countByUniversity(University university);
}
