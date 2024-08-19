package com.belajar.restapi.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name required")
    private String firstName;

    private String lastName;

    @Size(max = 255, message = "Bio should not exceed 255 characters")
    private String bio;

    @OneToOne(mappedBy = "profile_fk")
    private User user;
}
