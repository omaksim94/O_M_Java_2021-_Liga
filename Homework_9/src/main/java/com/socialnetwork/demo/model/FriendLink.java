package com.socialnetwork.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

/**
 * Таблица для создания связей друзей
 * Первичный ключ строка из UUID пользователей с разделителем ":"
 * UUID друзей осортируются, наименьший ставится первым и сохраняется
 * Позволяет найти все записи о друзьях пользователя за один запрос
 * SELECT * FROM friendlist WHERE friendlink_id LIKE '%personId%'
 */
@Getter
@Setter
@Entity
@Table(name = "friendlist")
public class FriendLink {
    @Id
    @Column(name = "friendlink_id")
    private String friendlinkId;
    @JoinColumn(name = "person_uid")
    @ManyToOne
    private Person person;
    @JoinColumn(name = "friend_uid")
    @ManyToOne
    private Person friend;

    public FriendLink(Person person, Person friend) {
        UUID personId = person.getId();
        UUID friendId = friend.getId();
        if (personId.compareTo(friendId) <= 0) {
            this.friendlinkId = "" + personId + ":" + friendId;
        } else if (personId.compareTo(friendId) == 1) {
            this.friendlinkId = "" + friendId + ":" + personId;
        }
        this.person = person;
        this.friend = friend;
    }

    public FriendLink() {

    }

    @Override
    public String toString() {
        return "FriendLink{" +
                "friendship_id='" + friendlinkId + '\'' +
                ", person=" + person +
                ", friend=" + friend +
                '}';
    }
}
