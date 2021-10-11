package com.socialnetwork.demo.service;

import com.socialnetwork.demo.model.School;
import com.socialnetwork.demo.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;


    public School getSchool(UUID school_uid) {
        return schoolRepository.getById(school_uid);
    }

    public List<School> getSchools() {
        return schoolRepository.findAll();
    }

    @Transactional
    public void editSchool(UUID schoolId, Map<String, String> json) {
        School editedSchool = schoolRepository.getById(schoolId);
        if (json.containsKey("school_name")) {
            editedSchool.setSchool_name(json.get("school_name"));
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
