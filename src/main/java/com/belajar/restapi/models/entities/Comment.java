package com.belajar.restapi.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @CreationTimestamp
    private LocalDateTime commentDateCreation;

    @UpdateTimestamp
    private LocalDateTime commentDateUpdated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_FK")
    @JsonManagedReference
    private User userFK; // FK from user class or tbl_user

}
