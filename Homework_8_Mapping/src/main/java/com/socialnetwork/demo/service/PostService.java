package com.socialnetwork.demo.service;

import com.socialnetwork.demo.model.Person;
import com.socialnetwork.demo.model.Post;
import com.socialnetwork.demo.repository.PersonRepository;
import com.socialnetwork.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PersonRepository personRepository;

    @Autowired
    public PostService(PostRepository postRepository, PersonRepository personRepository) {
        this.postRepository = postRepository;
        this.personRepository = personRepository;
    }


    public void addPost(UUID personId, String description) {
        if (description != null) {
            Optional<Person> optionalPerson = personRepository.findById(personId);
            if (!optionalPerson.isPresent()) {
                throw new EntityNotFoundException("Something went wrong");
            }
        }
        postRepository.save(new Post(description, personRepository.getById(personId)));
    }
}
