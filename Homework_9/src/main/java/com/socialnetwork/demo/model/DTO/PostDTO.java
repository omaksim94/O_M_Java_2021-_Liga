package com.socialnetwork.demo.model.DTO;

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
        this.AuthorFirstName = post.getPerson().getFirstName();
        this.AuthorLastName = post.getPerson().getLastName();
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
