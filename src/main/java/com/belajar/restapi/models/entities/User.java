package com.belajar.restapi.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username required")
    @Column(unique = true)
    @Size(min = 5)
    private String username;

    @NotBlank(message = "Email required")
    @Email(message = "Email must match the pattern")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 8)
    private String password;

    @OneToOne()
    @JoinColumn(name = "profileID")
    private Profile profile_fk;

    @OneToMany(mappedBy = "user_fk", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();
}
