package com.socialnetwork.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    @Column(unique = true, length = 16, name = "person_uid")
    protected UUID id;
    @Column(nullable = false, length = 100)
    private String first_name;
    @Column(nullable = false, length = 100)
    private String last_name;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false, length = 6)
    private String gender;
    @JoinColumn(name = "school_uid")
    @ManyToOne
    private School school;
    @Column
    @OneToMany(mappedBy = "person")
    private List<Post> personPosts;

    @ManyToMany()
    @JoinTable(name = "friendlist",
            joinColumns = @JoinColumn(name = "person_uid"),
            inverseJoinColumns = @JoinColumn(name = "friend_uid"))
    private Set<Person> friends;
    @ManyToMany(mappedBy = "friends")
    private Set<Person> friendOf;


    public Person(String first_name, String last_name, Integer age, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.gender = gender;
    }

    public Person(String first_name, String last_name, Integer age, String gender, School school) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.gender = gender;
        this.school = school;
    }

    public Person() {
    }

}
