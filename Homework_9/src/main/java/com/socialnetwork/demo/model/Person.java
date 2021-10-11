package com.socialnetwork.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    @BatchSize(size = 10)
    private Set<Post> personPosts;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    @BatchSize(size = 10)
    private Set<FriendLink> friendList;


    public Person(String first_name,
                  String last_name,
                  Integer age,
                  String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.gender = gender;
    }

    public Person(String first_name,
                  String last_name,
                  Integer age,
                  String gender,
                  School school) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.gender = gender;
        this.school = school;
    }


    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", school=" + school +
                ", personPosts=" + personPosts +
                ", friendList=" + friendList +
                '}';
    }
}
