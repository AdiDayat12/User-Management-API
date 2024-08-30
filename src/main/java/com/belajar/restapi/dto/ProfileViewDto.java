package com.belajar.restapi.dto;

import com.belajar.restapi.models.entities.Address;
import com.belajar.restapi.models.entities.User;
import lombok.Data;

@Data
public class ProfileViewDto {
    private String firstName;

    private String lastName;

    private String bio;
    private String avatarUrl;
    private User user;
    private Address address;
}
