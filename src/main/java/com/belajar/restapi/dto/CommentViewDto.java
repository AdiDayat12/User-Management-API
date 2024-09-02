package com.belajar.restapi.dto;

import com.belajar.restapi.models.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
public class CommentViewDto {

    private String username;

    private List<String> text;

    private LocalDateTime commentDateCreation;

    private LocalDateTime commentDateUpdated;

    public CommentViewDto(User user, String text, LocalDateTime commentDateCreation, LocalDateTime commentDateUpdated) {
        this.username = user != null ? user.getUsername() : "Unknown";
        this.text = Collections.singletonList(text);
        this.commentDateCreation = commentDateCreation;
        this.commentDateUpdated = commentDateUpdated;
    }
}
