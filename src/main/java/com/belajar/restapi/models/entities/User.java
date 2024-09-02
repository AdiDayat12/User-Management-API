package com.belajar.restapi.models.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @OneToOne(mappedBy = "userFK", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Profile profile;


    @OneToMany(mappedBy = "userFK", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonBackReference
    private List<Comment> commentList = new ArrayList<>();


}

