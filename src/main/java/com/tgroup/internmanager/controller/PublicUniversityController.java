package com.tgroup.internmanager.controller.publicapi;

import com.tgroup.internmanager.mapper.UniversityMapper;
import com.tgroup.internmanager.model.dto.university.UniversityShortDTO;
import com.tgroup.internmanager.service.IUniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/universities")
@RequiredArgsConstructor
public class PublicUniversityController {

    private final IUniversityService universityService;
    private final UniversityMapper universityMapper;

    @GetMapping
    public List<UniversityShortDTO> getAllUniversities() {
        return universityService.getAll()
                .stream()
                .map(universityMapper::toShortDTO)
                .toList();
    }
}
