package com.belajar.restapi.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentData {
    private Long userID;

    @Size(max = 500, message = "Comment should not exceed 500 characters")
    private String text;

}
