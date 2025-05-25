package com.tgroup.internmanager.mapper;

import com.tgroup.internmanager.model.dto.university.*;
import com.tgroup.internmanager.model.entity.University;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UniversityMapper {

    UniversityResponseDTO toResponseDTO(University university);

    UniversityShortDTO toShortDTO(University university);

    @Mapping(target = "universityId", ignore = true)
    @Mapping(target = "students", ignore = true)
    University toEntity(UniversityCreateRequestDTO dto);
}
