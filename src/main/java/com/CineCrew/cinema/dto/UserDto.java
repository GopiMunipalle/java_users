package com.CineCrew.cinema.dto;

import io.micrometer.common.lang.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank()
    private String email;
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    private String firstName;
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    private String lastName;
    @NotBlank()
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @Nullable
    private String bio;
    @Nullable
    private String profileImage;
    @Nullable
    private String country;
    @Nullable
    private String city;
    @NotBlank()
    private Integer roleId;
    @Nullable()
    private Boolean isActive;
    @Nullable
    private Boolean isVerified;
    @Nullable
    private String fcmToken;

}