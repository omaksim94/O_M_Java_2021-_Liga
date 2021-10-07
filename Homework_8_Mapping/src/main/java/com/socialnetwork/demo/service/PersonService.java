package com.socialnetwork.demo.service;

import com.socialnetwork.demo.model.Person;
import com.socialnetwork.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public Optional<Person> getPerson(UUID personId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if (optionalPerson.isEmpty()) {
            throw new IllegalStateException("No person with ID: " + personId);
        }
        return optionalPerson;
    }

    public void addFriend(UUID personId, UUID friendId) {
        Person person = personRepository.getById(personId);
        Person newFriend = personRepository.getById(friendId);
        if (person.equals(newFriend)) {
            throw new IllegalStateException("User can't be his own friend");
        }
        if (!person.getFriends().contains(newFriend)) {
            person.getFriends().add(newFriend);
            newFriend.getFriends().add(person);
            personRepository.saveAll(List.of(person, newFriend));
        }
    }

    public void removeFriend(UUID personId, UUID friendId) {
        Person person = personRepository.getById(personId);
        Person friend = personRepository.getById(friendId);
        person.getFriends().remove(friend);
        friend.getFriends().remove(person);
        personRepository.saveAll(List.of(person, friend));
    }
}
