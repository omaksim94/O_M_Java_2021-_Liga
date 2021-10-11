package com.socialnetwork.demo.service;

import com.socialnetwork.demo.model.FriendLink;
import com.socialnetwork.demo.model.Person;
import com.socialnetwork.demo.model.School;
import com.socialnetwork.demo.repository.FriendLinkRepository;
import com.socialnetwork.demo.repository.PersonRepository;
import com.socialnetwork.demo.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final SchoolRepository schoolRepository;
    private final FriendLinkRepository friendLinkRepository;

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

    @Transactional
    public void addNewPerson(Map<String, String> json) {
        Person newPerson = new Person();
        newPerson.setFirst_name(json.get("first_name"));
        newPerson.setLast_name(json.get("last_name"));
        newPerson.setAge(Integer.parseInt(json.get("age")));
        newPerson.setGender(json.get("gender"));
        if (json.containsKey("school")) {
            School school = schoolRepository.getById(UUID.fromString(json.get("school")));
            newPerson.setSchool(school);
        }
        personRepository.save(newPerson);
    }

    @Transactional
    public void deletePerson(UUID personId) {
        Person personToDelete = personRepository.getById(personId);
        personRepository.delete(personToDelete);
    }

    @Transactional
    public void editPerson(UUID personId, Map<String, String> json) {
        Person editedPerson = personRepository.getById(personId);
        if (json.containsKey("first_name")) {
            editedPerson.setFirst_name(json.get("first_name"));
        }

        if (json.containsKey("last_name")) {
            editedPerson.setLast_name(json.get("last_name"));
        }

        if (json.containsKey("age")) {
            editedPerson.setAge(Integer.parseInt(json.get("age")));
        }

        if (json.containsKey("gender")) {
            editedPerson.setGender(json.get("gender"));
        }

        if (json.containsKey("school")) {
            School school = schoolRepository.getById(UUID.fromString(json.get("school")));
            editedPerson.setSchool(school);
        }
        personRepository.save(editedPerson);
    }

    @Transactional
    public void addFriend(UUID personId, UUID friendId) {
        Person person = personRepository.getById(personId);
        Person newFriend = personRepository.getById(friendId);
        FriendLink friendLink = new FriendLink(person, newFriend);
        person.getFriendList().add(friendLink);
        friendLinkRepository.save(friendLink);
    }

    @Transactional
    public void removeFriend(UUID personId, UUID friendId) {
        String friendLink_id = createFriendLinkId(personId, friendId);

        if (friendLinkRepository.findById(friendLink_id).isPresent()) {
            friendLinkRepository.deleteById(friendLink_id);
        }
    }

    public String createFriendLinkId(UUID personId, UUID friendId) {
        String friendLink_id = null;
        if (personId.compareTo(friendId) <= 0) {
            friendLink_id = "" + personId + ":" + friendId;
        } else if (personId.compareTo(friendId) == 1) {
            friendLink_id = "" + friendId + ":" + personId;
        }
        return friendLink_id;
    }

    /**
     * Берет список друзей пользователя
     * Для каждой строчки проверяет, совпадает ли Id пользователя и Id пользователя в колонках списка друзей
     * Из двух Id берет, то, которое не совпадает с Id пользователя
     * Собирает список друзей в лист
     */
    @Transactional
    public List<Person> getFriends(UUID personId) {

        return personRepository.getById(personId).getFriendList().stream()
                .map(link -> {
                    if (link.getPerson().getId().equals(personId)) {
                        return link.getFriend();
                    } else {
                        return link.getPerson();
                    }
                })
                .collect(Collectors.toList());
    }
}
