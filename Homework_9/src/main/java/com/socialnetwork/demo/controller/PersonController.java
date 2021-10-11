package com.socialnetwork.demo.controller;

import com.socialnetwork.demo.DTO.PersonDTO;
import com.socialnetwork.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PersonController {
    private final PersonService personService;

    @GetMapping(path = "/people")
    public List<PersonDTO> getPeople() {
        return personService.getPeople().stream()
                .map(person -> new PersonDTO(person))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{personId}")
    public PersonDTO getPerson(@PathVariable("personId") UUID personId) {
        return new PersonDTO(personService.getPerson(personId).get());
    }

    @PostMapping(path = "/person/new")
    public void addNewPerson(@RequestBody Map<String, String> json) {
        personService.addNewPerson(json);
    }

    @PutMapping(path = "/{personId}/edit")
    public void editPerson(@PathVariable("personId") UUID personId,
                           @RequestBody Map<String, String> json) {
        personService.editPerson(personId, json);
    }

    @DeleteMapping(path = "/{personId}/delete")
    public void deletePerson(@PathVariable("personId") UUID personId) {
        personService.deletePerson(personId);
    }

    @PutMapping(path = "/{personId}/friends/new")
    public void addFriend(@PathVariable("personId") UUID personId,
                          @RequestParam(required = true) UUID friendId) {
        personService.addFriend(personId, friendId);
    }

    @DeleteMapping(path = "/{personId}/friends/delete")
    public void removeFriend(@PathVariable("personId") UUID personId,
                             @RequestParam(required = true) UUID friendId) {
        personService.removeFriend(personId, friendId);
    }

    @GetMapping(path = "{personId}/friends")
    public List<PersonDTO> getFriends(@PathVariable("personId") UUID personId) {
        return personService.getFriends(personId).stream()
                .map(person -> new PersonDTO(person))
                .collect(Collectors.toList());
    }

}
