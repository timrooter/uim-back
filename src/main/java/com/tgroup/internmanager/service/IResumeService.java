package com.tgroup.internmanager.service;

import com.tgroup.internmanager.model.entity.Resume;

public interface IResumeService {
    Resume createResume(String studentId, Resume resume);
    Resume getResumeByStudent(String studentId);
    void deleteResume(String studentId);
}
