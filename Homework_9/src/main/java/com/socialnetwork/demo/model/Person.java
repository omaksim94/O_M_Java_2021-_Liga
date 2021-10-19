package com.socialnetwork.demo.model;

import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @password - пароли у всех пользователей в БД = password
 */
@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue
    @Column(unique = true, length = 16, name = "person_uid")
    protected UUID id;
    @Column(nullable = false, length = 100, name = "first_name")
    private String firstName;
    @Column(nullable = false, length = 100, name = "last_name")
    private String lastName;
    @Column(nullable = false, name = "age")
    private Integer age;
    @Column(nullable = false, length = 6, name = "gender")
    private String gender;
    @JoinColumn(name = "school_uid")
    @ManyToOne
    private School school;
    @Column
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    @BatchSize(size = 50)
    private Set<Post> personPosts;
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    @BatchSize(size = 50)
    private Set<FriendLink> friendList;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private boolean isAccountNonExpired;
    @Column
    private boolean isAccountNonLocked;
    @Column
    private boolean isCredentialsNonExpired;
    @Column
    private boolean isEnabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    private List<Authorities> authorities;

    public Person(String first_name,
                  String last_name,
                  Integer age,
                  String gender) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.age = age;
        this.gender = gender;
    }

    public Person(String first_name,
                  String last_name,
                  Integer age,
                  String gender,
                  School school) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.age = age;
        this.gender = gender;
        this.school = school;
    }


    public Person() {
    }

}
