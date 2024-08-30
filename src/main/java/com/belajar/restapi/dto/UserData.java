package com.belajar.restapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserData {
    @NotBlank(message = "Username required")
    @Column(unique = true)
    @Size(min = 5)
    private String username;

    @NotBlank(message = "Email required")
    @Email(message = "Email is invalid")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 8)
    private String password;
}
