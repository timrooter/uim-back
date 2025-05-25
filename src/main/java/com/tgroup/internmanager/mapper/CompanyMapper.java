package com.tgroup.internmanager.mapper;

import com.tgroup.internmanager.model.dto.company.*;
import com.tgroup.internmanager.model.entity.Company;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyResponseDTO toResponseDTO(Company company);

    CompanyShortDTO toShortDTO(Company company);

    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "vacancies", ignore = true)
    Company toEntity(CompanyCreateRequestDTO dto);
}
