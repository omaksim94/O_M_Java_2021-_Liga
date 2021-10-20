package com.socialnetwork.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Dialogue {
    @Id
    @Column(unique = true, name = "id")
    protected String id;
    @JoinColumn(name = "sender_uid")
    @ManyToOne
    private Person person1;
    @JoinColumn(name = "receiver_uid")
    @ManyToOne
    private Person person2;
    @OneToMany(mappedBy = "dialogue", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @BatchSize(size = 100)
    private List<Message> messageList = new ArrayList<>();

    public Dialogue(String id, Person person1, Person person2) {
        this.id = id;
        this.person1 = person1;
        this.person2 = person2;
    }

    public Dialogue() {
    }
}
