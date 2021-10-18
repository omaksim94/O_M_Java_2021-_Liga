package com.socialnetwork.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Set;
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
    @Column(nullable = false, name = "school_name")
    private String schoolName;
    @Column(unique = true, length = 1024, name = "address")
    private String address;
    @Column
    @OneToMany(mappedBy = "school")
    @BatchSize(size = 10)
    private Set<Person> person;

    public School(String school_name, String address) {
        this.schoolName = school_name;
        this.address = address;
    }

    public School() {
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", school_name='" + schoolName + '\'' +
                ", address='" + address + '\'' +
                ", person=" + person +
                '}';
    }
}
