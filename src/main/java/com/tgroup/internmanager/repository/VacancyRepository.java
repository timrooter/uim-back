package com.tgroup.internmanager.repository;

import com.tgroup.internmanager.model.entity.Company;
import com.tgroup.internmanager.model.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, String> {
    List<Vacancy> findAllByCompany(Company company);
    Optional<Vacancy> findByVacancyId(String vacancyId);

    List<Vacancy> findByTitleContainingIgnoreCase(String keyword);
    List<Vacancy> findByCategory(String category);

    List<Vacancy> findByCompany(Company company);

}
