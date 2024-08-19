package com.belajar.restapi.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 500, message = "Comment should not exceed 500 characters")
    private String text;

    private LocalDate commentDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user_fk;
}
