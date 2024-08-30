package com.belajar.restapi.dto;

import com.belajar.restapi.models.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
public class ProfileData {
    @NotBlank(message = "First name required")
    private String firstName;

    @NotBlank(message = "Last name required")
    private String lastName;

    private String bio;

    @URL(message = "Avatar URL must be a valid URL")
    private String avatarUrl;

    @Pattern(regexp = "\\d{10}", message = "Phone must be a 10-digit number")
    private String phone;

    @Valid
    private AddressDto address;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    private Long userID;
}
