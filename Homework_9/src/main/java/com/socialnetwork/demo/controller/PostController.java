package com.socialnetwork.demo.controller;

import com.socialnetwork.demo.model.DTO.PostDTO;
import com.socialnetwork.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/posts/")
public class PostController {
    private final PostService postService;

    @GetMapping(path = "{personId}")
    public List<PostDTO> getUserPosts(@PathVariable("personId") UUID personId) {
        return postService.getUserPosts(personId);
    }

    @GetMapping(path = "{personId}/feed")
    public List<PostDTO> getUserFriendsPosts(@PathVariable("personId") UUID personId) {
        return postService.getUserFriendsPosts(personId);
    }

    @PostMapping(path = "{personId}")
    public void addPost(@PathVariable("personId") UUID personId,
                        @RequestBody String description) {
        postService.addPost(personId, description);
    }

    @PutMapping(path = "{postId}")
    public void editPost(@PathVariable("postId") UUID postId,
                         @RequestBody String description) {
        postService.editPost(postId, description);
    }

    @DeleteMapping(path = "{postId}")
    public void deletePost(@PathVariable("postId") UUID postId) {
        postService.deletePost(postId);
    }
}
