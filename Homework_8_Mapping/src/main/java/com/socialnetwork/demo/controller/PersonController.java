package com.socialnetwork.demo.controller;

import com.socialnetwork.demo.model.Person;
import com.socialnetwork.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @GetMapping(path = "{personId}")
    public Optional<Person> getPerson(@PathVariable("personId") UUID personId) {
        return personService.getPerson(personId);
    }

    @PutMapping(path = "{personId}")
    public void addFriend(@PathVariable("personId") UUID personId,
                          @RequestParam(required = true) UUID friendId) {
        personService.addFriend(personId, friendId);
    }

    @DeleteMapping(path = "{personId}")
    public void removeFriend(@PathVariable("personId") UUID personId,
                             @RequestParam(required = true) UUID friendId) {
        personService.removeFriend(personId, friendId);
    }
}
