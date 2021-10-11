package com.socialnetwork.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_uid", unique = true, length = 16)
    protected UUID id;
    @Column(nullable = false, length = 1024)
    private String description;
    @JoinColumn(name = "person_uid")
    @ManyToOne
    private Person person;

    public Post(String description, Person person) {
        this.description = description;
        this.person = person;
    }

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", person=" + person +
                '}';
    }
}
