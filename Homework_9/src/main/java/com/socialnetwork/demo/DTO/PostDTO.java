package com.socialnetwork.demo.DTO;

import com.socialnetwork.demo.model.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostDTO {
    private UUID postId;
    private String AuthorFirstName;
    private String AuthorLastName;
    private String description;

    public PostDTO(Post post) {
        this.postId = post.getId();
        this.AuthorFirstName = post.getPerson().getFirst_name();
        this.AuthorLastName = post.getPerson().getLast_name();
        this.description = post.getDescription();
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", AuthorFirstName='" + AuthorFirstName + '\'' +
                ", AuthorLastName='" + AuthorLastName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
