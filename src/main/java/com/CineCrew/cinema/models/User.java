package com.CineCrew.cinema.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    private String bio;

    private String profileImage;

    private String country;

    private String city;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private boolean isActive;

    private boolean isVerified;

    private String fcmToken;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_posts", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "post_id")
    private List<Long> postIds;

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.isActive = true; // Default new users as active
        this.isVerified = false; // Not verified initially
    }

}
