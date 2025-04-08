package com.CineCrew.cinema.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    private boolean isActive;

    private boolean isVerified;

    private String fcmToken;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_posts", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "post_id")
    private List<Long> postIds;

    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private int roleId;

}
