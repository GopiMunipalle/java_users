package com.CineCrew.cinema.dto;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

import java.util.Set;

public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String bio;
    private String profileImage;
    private String country;
    private String city;
    // private Set<String> roleNames;

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getBio() {
        return this.bio;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }

    public String getRoleNames() {
        // return this.roleNames;
        return "ADMIN";
    }
}