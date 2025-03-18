package com.CineCrew.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CineCrew.cinema.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
