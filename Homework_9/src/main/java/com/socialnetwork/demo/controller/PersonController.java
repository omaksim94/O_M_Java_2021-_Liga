package com.socialnetwork.demo.controller;

import com.socialnetwork.demo.model.DTO.PersonDTO;
import com.socialnetwork.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/people")
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public Page<PersonDTO> getPeople(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "firstName, asc") String[] sort) {
        return personService.getPeople(page, size, sort);
    }

    @GetMapping(path = "/{personId}")
    public PersonDTO getPerson(@PathVariable("personId") UUID personId) {
        return personService.getPerson(personId);
    }

    @PostMapping
    public void addNewPerson(@RequestBody Map<String, String> json) {
        personService.addNewPerson(json);
    }

    @PutMapping(path = "/{personId}")
    public void editPerson(@PathVariable("personId") UUID personId,
                           @RequestBody Map<String, String> json) {
        personService.editPerson(personId, json);
    }

    @DeleteMapping(path = "/{personId}")
    public void deletePerson(@PathVariable("personId") UUID personId) {
        personService.deletePerson(personId);
    }

    @PostMapping(path = "/{personId}/friends")
    public void addFriend(@PathVariable("personId") UUID personId,
                          @RequestParam(required = true) UUID friendId) {
        personService.addFriend(personId, friendId);
    }

    @DeleteMapping(path = "/{personId}/friends")
    public void removeFriend(@PathVariable("personId") UUID personId,
                             @RequestParam(required = true) UUID friendId) {
        personService.removeFriend(personId, friendId);
    }

    @GetMapping(path = "/{personId}/friends")
    public List<PersonDTO> getFriends(@PathVariable("personId") UUID personId) {
        return personService.getFriends(personId);
    }

    @PutMapping(path = "/{personId}/role")
    public void addRole(@PathVariable("personId") UUID personId,
                        @RequestParam(required = true) Long roleId){
        personService.addRole(personId, roleId);
    }

}