package com.league.exam.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "roles")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Identifiable {
    @Column(name = "role")
    private String roleName;
    @OneToMany(mappedBy = "role")
    private Set<User> user;

}
