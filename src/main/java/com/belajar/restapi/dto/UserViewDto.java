package com.belajar.restapi.dto;

import com.belajar.restapi.models.entities.Comment;
import com.belajar.restapi.models.entities.Profile;
import lombok.Data;

import java.util.List;

@Data
public class UserViewDto {
    private Long id;

    private String username;

    private String email;

    private String password;

    private Profile profile;

    private List<Comment> commentList;
}
