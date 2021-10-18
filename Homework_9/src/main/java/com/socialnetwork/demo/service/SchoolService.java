package com.socialnetwork.demo.service;

import com.socialnetwork.demo.model.DTO.SchoolDTO;
import com.socialnetwork.demo.model.School;
import com.socialnetwork.demo.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchoolService {
    private final SchoolRepository schoolRepository;


    public SchoolDTO getSchool(UUID schoolId) {
        return new SchoolDTO(schoolRepository.getById(schoolId));
    }

    public List<SchoolDTO> getSchools() {
        return schoolRepository.findAll().stream()
                .map(SchoolDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void editSchool(UUID schoolId, Map<String, String> json) {
        School editedSchool = schoolRepository.getById(schoolId);
        if (json.containsKey("school_name")) {
            editedSchool.setSchoolName(json.get("school_name"));
        }

        if (json.containsKey("address")) {
            editedSchool.setAddress(json.get("address"));
        }
        schoolRepository.save(editedSchool);
    }

    @Transactional
    public void deleteSchool(UUID schoolId) {
        schoolRepository.deleteById(schoolId);
    }
}
