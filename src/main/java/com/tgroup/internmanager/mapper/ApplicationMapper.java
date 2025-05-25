package com.tgroup.internmanager.mapper;

import com.tgroup.internmanager.model.dto.application.*;
import com.tgroup.internmanager.model.entity.Application;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        com.tgroup.internmanager.mapper.ResumeMapper.class,
        com.tgroup.internmanager.mapper.VacancyMapper.class
})
public interface ApplicationMapper {

    @Mapping(source = "student.studentId", target = "studentId")
    ApplicationResponseDTO toResponseDTO(Application app);
    ApplicationShortDTO toShortDTO(Application app);


}
