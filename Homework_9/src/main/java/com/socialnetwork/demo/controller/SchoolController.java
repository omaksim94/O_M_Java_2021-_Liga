package com.socialnetwork.demo.controller;

import com.socialnetwork.demo.model.DTO.SchoolDTO;
import com.socialnetwork.demo.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping
    public List<SchoolDTO> getSchools() {
        return schoolService.getSchools();
    }

    @GetMapping(path = "/{school_uid}")
    public SchoolDTO getSchool(@PathVariable("school_uid") UUID schoolId) {
        return schoolService.getSchool(schoolId);
    }

    @PutMapping(path = "/{schoolId}")
    public void editSchool(@PathVariable("schoolId") UUID schoolId,
                           @RequestBody Map<String, String> json) {
        schoolService.editSchool(schoolId, json);
    }

    @DeleteMapping(path = "/{schoolId}")
    public void deleteSchool(@PathVariable UUID schoolId) {
        schoolService.deleteSchool(schoolId);
    }
}
