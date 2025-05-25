package com.tgroup.internmanager.repository;

import com.tgroup.internmanager.model.entity.Resume;
import com.tgroup.internmanager.model.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, String> {
    Optional<Resume> findByStudent(Student student);

    @Query("SELECT r FROM Resume r WHERE r.student.studentId = :studentId")
    Optional<Resume> findByStudentId(@Param("studentId") String studentId);

    boolean existsByStudent(Student student);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM resumes WHERE resume_id = :resumeId", nativeQuery = true)
    void deleteByResumeIdNative(String resumeId);
}

