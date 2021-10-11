package com.socialnetwork.demo.controller;

import com.socialnetwork.demo.DTO.SchoolDTO;
import com.socialnetwork.demo.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping
    public List<SchoolDTO> getSchools() {
        return schoolService.getSchools().stream()
                .map(school -> new SchoolDTO(school))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{school_uid}")
    public SchoolDTO getSchool(@PathVariable("school_uid") UUID school_uid) {
        return new SchoolDTO(schoolService.getSchool(school_uid));
    }

    @PutMapping(path = "/edit/{schoolId}")
    public void editSchool(@PathVariable("schoolId") UUID schoolId,
                           @RequestBody Map<String, String> json) {
        schoolService.editSchool(schoolId, json);
    }

    @DeleteMapping(path = "/delete/{schoolId}")
    public void deleteSchool(@PathVariable UUID schoolId) {
        schoolService.deleteSchool(schoolId);
    }
}
