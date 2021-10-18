package com.socialnetwork.demo.DTO;

import com.socialnetwork.demo.model.School;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SchoolDTO {
    private UUID schoolId;
    private String school_name;
    private String address;

    public SchoolDTO(School school) {
        this.schoolId = school.getId();
        this.school_name = school.getSchool_name();
        this.address = school.getAddress();
    }

    @Override
    public String toString() {
        return "SchoolDTO{" +
                "schoolId=" + schoolId +
                ", school_name='" + school_name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
