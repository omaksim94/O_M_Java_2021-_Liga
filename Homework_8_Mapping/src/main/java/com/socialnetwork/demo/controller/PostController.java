package com.socialnetwork.demo.controller;

import com.socialnetwork.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "{personId}")
    public void addPost(@PathVariable("personId") UUID personId,
                        @RequestParam(required = true) String description) {
        postService.addPost(personId, description);
    }
}
