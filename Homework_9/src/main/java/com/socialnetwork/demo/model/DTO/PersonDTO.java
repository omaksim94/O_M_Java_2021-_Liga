package com.socialnetwork.demo.model.DTO;

import com.socialnetwork.demo.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class PersonDTO {
    private UUID personId;
    private String first_name;
    private String last_name;
    private int age;
    private String gender;
    private String schoolname;

    public PersonDTO(Person person) {
        this.personId = person.getId();
        this.first_name = person.getFirstName();
        this.last_name = person.getLastName();
        this.age = person.getAge();
        this.gender = person.getGender();
        if (person.getSchool() != null) {
            this.schoolname = person.getSchool().getSchoolName();
        } else {
            this.schoolname = "Not set";
        }
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "personId=" + personId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", schoolname='" + schoolname + '\'' +
                '}';
    }
}
