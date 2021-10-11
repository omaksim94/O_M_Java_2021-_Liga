package com.socialnetwork.demo.service;

import com.socialnetwork.demo.model.Person;
import com.socialnetwork.demo.model.Post;
import com.socialnetwork.demo.repository.PersonRepository;
import com.socialnetwork.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PersonRepository personRepository;

    @Transactional
    public void addPost(UUID personId, String description) {
        if (description != null) {
            Optional<Person> optionalPerson = personRepository.findById(personId);
            if (optionalPerson.isEmpty()) {
                throw new EntityNotFoundException("Something went wrong");
            }
        }
        postRepository.save(new Post(description, personRepository.getById(personId)));
    }

    @Transactional
    public Set<Post> getUserPosts(UUID personId) {
        Set<Post> posts = personRepository.getById(personId).getPersonPosts();
        return posts;
    }

    @Transactional
    public Set<Post> getUserFriendsPosts(UUID personId) {
        Person user = personRepository.getById(personId);
        return user.getFriendList().stream()
                .map(link -> {
                    if (link.getPerson().getId().equals(personId)) {
                        return link.getFriend();
                    } else {
                        return link.getPerson();
                    }
                })
                .flatMap(friend -> friend.getPersonPosts().stream())
                .collect(Collectors.toSet());
    }

    @Transactional
    public void deletePost(UUID postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public void editPost(UUID postId, String description) {
        Post post = postRepository.getById(postId);
        post.setDescription(description);
        postRepository.save(post);
    }
}
