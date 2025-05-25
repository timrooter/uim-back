package com.tgroup.internmanager.mapper;

import com.tgroup.internmanager.model.dto.vacancy.*;
import com.tgroup.internmanager.model.entity.Vacancy;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VacancyMapper {

    @Mapping(source = "company.companyId", target = "companyId")
    @Mapping(source = "companyName", target = "companyName")
    VacancyResponseDTO toResponseDTO(Vacancy vacancy);
    VacancyShortDTO toShortDTO(Vacancy vacancy);

    @Mapping(target = "vacancyId", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "companyName", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Vacancy toEntity(VacancyCreateRequestDTO dto);
}
