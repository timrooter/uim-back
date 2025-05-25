package com.tgroup.internmanager.mapper;

import com.tgroup.internmanager.model.dto.student.*;
import com.tgroup.internmanager.model.entity.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "university.universityName", target = "universityName")
    StudentResponseDTO toResponseDTO(Student student);

    StudentShortDTO toShortDTO(Student student);

    @Mapping(target = "studentId", ignore = true)
    @Mapping(target = "university", ignore = true)
    @Mapping(target = "resume", ignore = true)
    @Mapping(target = "applications", ignore = true)
    Student toEntity(StudentCreateRequestDTO dto);
}
