package com.socialnetwork.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "school")
@Getter
@Setter
public class School {
    @Id
    @GeneratedValue
    @Column(name = "school_uid", unique = true, length = 16)
    protected UUID id;
    @Column(nullable = false)
    private String school_name;
    @Column(unique = true, length = 1024)
    private String address;
    @Column
    @OneToMany(mappedBy = "school")
    private List<Person> person;

    public School(String school_name, String address) {
        this.school_name = school_name;
        this.address = address;
    }

    public School() {
    }
}
