package com.socialnetwork.demo.service;

import com.socialnetwork.demo.model.Authorities;
import com.socialnetwork.demo.model.DTO.AccountDetails;
import com.socialnetwork.demo.model.DTO.PersonDTO;
import com.socialnetwork.demo.model.FriendLink;
import com.socialnetwork.demo.model.Person;
import com.socialnetwork.demo.model.School;
import com.socialnetwork.demo.repository.AuthoritiesRepository;
import com.socialnetwork.demo.repository.FriendLinkRepository;
import com.socialnetwork.demo.repository.PersonRepository;
import com.socialnetwork.demo.repository.SchoolRepository;
import com.socialnetwork.demo.security.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final SchoolRepository schoolRepository;
    private final FriendLinkRepository friendLinkRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final PasswordEncoder passwordEncoder = SecurityConfiguration.encoder();


    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public PersonDTO getPerson(UUID personId) {
        return personRepository.findById(personId)
                .map(PersonDTO::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void addNewPerson(Map<String, String> json) {
        Person newPerson = new Person();
        newPerson.setFirstName(json.get("first_name"));
        newPerson.setLastName(json.get("last_name"));
        newPerson.setAge(Integer.parseInt(json.get("age")));
        newPerson.setGender(json.get("gender"));
        newPerson.setUsername(json.get("username"));
        newPerson.setPassword(passwordEncoder.encode(json.get("password")));
        newPerson.setAccountNonExpired(true);
        newPerson.setAccountNonLocked(true);
        newPerson.setCredentialsNonExpired(true);
        newPerson.setEnabled(true);
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
            editedPerson.setFirstName(json.get("first_name"));
        }

        if (json.containsKey("last_name")) {
            editedPerson.setLastName(json.get("last_name"));
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
        String friendLinkId = createFriendLinkId(personId, friendId);

        if (friendLinkRepository.findById(friendLinkId).isPresent()) {
            friendLinkRepository.deleteById(friendLinkId);
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
    public List<PersonDTO> getFriends(UUID personId) {

         List<Person> personList = personRepository.getById(personId).getFriendList().stream()
                .map(link -> {
                    if (link.getPerson().getId().equals(personId)) {
                        return link.getFriend();
                    } else {
                        return link.getPerson();
                    }
                })
                .collect(Collectors.toList());

        return personList.stream()
                .map(PersonDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> optionalAccount = personRepository.findByUsername(username);
        Person account = optionalAccount
                .orElseThrow(() -> new UsernameNotFoundException("User with name: " + username + " not found"));
        return new AccountDetails(account);
    }

    @Transactional
    public void addRole(UUID personId, Long roleId) {
        Authorities role = authoritiesRepository.getById(roleId);
        Person person = personRepository.getById(personId);
        person.getAuthorities().add(role);
        personRepository.save(person);
    }
}
