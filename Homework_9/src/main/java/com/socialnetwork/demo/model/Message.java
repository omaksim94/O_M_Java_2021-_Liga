package com.socialnetwork.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue
    @Column(unique = true, length = 16, name = "id")
    protected UUID id;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "sender_uid")
    @ManyToOne
    private Person sender;
    @JoinColumn(name = "receiver_uid")
    @ManyToOne
    private Person receiver;
    @JoinColumn(name = "dialogue_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Dialogue dialogue;

    public Message(LocalDateTime dateTime, String text, Person from, Person to, Dialogue dialogue) {
        this.dateTime = dateTime;
        this.text = text;
        this.sender = from;
        this.receiver = to;
        this.dialogue = dialogue;
    }

    public Message() {
    }
}
