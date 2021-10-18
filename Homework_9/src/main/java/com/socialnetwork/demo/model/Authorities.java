package com.socialnetwork.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authority")
@Setter
@Getter
public class Authorities {

    @Id
    @SequenceGenerator(
            name = "authority_sequence",
            sequenceName = "authority_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authority_sequence"
    )
    @Column(unique = true, name = "authority_uid")
    protected Long id;
    @Column(name = "authority")
    private String authority;
    @ManyToMany(mappedBy = "authorities")
    private List<Person> accounts;

    public Authorities(Long id, String authority, List<Person> users) {
        this.id = id;
        this.authority = authority;
        this.accounts = users;
    }

    public Authorities() {
    }
}
