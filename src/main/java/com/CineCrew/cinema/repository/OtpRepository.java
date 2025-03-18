package com.CineCrew.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CineCrew.cinema.models.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByEmail(String email);
}