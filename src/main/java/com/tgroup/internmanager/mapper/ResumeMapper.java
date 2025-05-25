package com.tgroup.internmanager.mapper;

import com.tgroup.internmanager.model.dto.resume.*;
import com.tgroup.internmanager.model.entity.Resume;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ResumeMapper {

    @Mapping(source = "student.studentId", target = "studentId")
    @Mapping(source = "student.fullName", target = "studentName")
    @Mapping(source = "student.university.universityName", target = "universityName")
    ResumeResponseDTO toResponseDTO(Resume resume);

    @Mapping(target = "resumeId", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Resume toEntity(ResumeCreateRequestDTO dto);
}
