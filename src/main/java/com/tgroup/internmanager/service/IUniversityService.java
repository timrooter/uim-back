package com.tgroup.internmanager.service;

import com.tgroup.internmanager.model.entity.Student;
import com.tgroup.internmanager.model.entity.University;

import java.util.List;
import java.util.Map;

public interface IUniversityService {
    List<Student> getStudentsByUniversity(String universityId);
    long countStudentsByUniversity(String universityId);
    Map<String, Long> getStudentStatusStats(String universityId);

    University getByEmail(String email);

    public List<University> getAll();

}
