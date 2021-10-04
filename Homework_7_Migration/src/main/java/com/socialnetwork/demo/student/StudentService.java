package com.socialnetwork.demo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JULY;

@Service
public class StudentService {
    public List<Student> getStudents(){
        return List.of(
                new Student(
                        1L,
                        "Mariam",
                        "mariam@gmail.com",
                        LocalDate.of(2000, JULY, 1),
                        21
                )
        );
    }
}
