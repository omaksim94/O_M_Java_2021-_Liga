package com.socialnetwork.demo.controller;

import com.socialnetwork.demo.DTO.PostDTO;
import com.socialnetwork.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/")
public class PostController {
    private final PostService postService;

    @GetMapping(path = "{personId}/posts")
    public List<PostDTO> getUserPosts(@PathVariable("personId") UUID personId) {
        return postService.getUserPosts(personId).stream()
                .map(post -> new PostDTO(post)
                )
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{personId}/feed")
    public List<PostDTO> getUserFriendsPosts(@PathVariable("personId") UUID personId) {
        return postService.getUserFriendsPosts(personId).stream()
                .map(post -> new PostDTO(post))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "{personId}/post/new")
    public void addPost(@PathVariable("personId") UUID personId,
                        @RequestBody String description) {
        postService.addPost(personId, description);
    }

    @PutMapping(path = "post/edit/{postId}")
    public void editPost(@PathVariable("postId") UUID postId,
                         @RequestBody String description) {
        postService.editPost(postId, description);
    }

    @DeleteMapping(path = "post/delete/{postId}")
    public void deletePost(@PathVariable("postId") UUID postId) {
        postService.deletePost(postId);
    }
}
