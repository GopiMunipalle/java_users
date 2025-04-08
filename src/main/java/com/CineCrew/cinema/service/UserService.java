package com.CineCrew.cinema.service;

import org.springframework.stereotype.Service;

import com.CineCrew.cinema.dto.UserDto;
// import com.CineCrew.cinema.models.Role;
import com.CineCrew.cinema.models.User;
import com.CineCrew.cinema.repository.RoleRepository;
import com.CineCrew.cinema.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(UserDto dto) {
        String email = dto.getEmail();

        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setEmail(email);
        String hashedPassword = hashPassword(dto.getPassword());
        user.setPassword(hashedPassword);
        user.setRoleId(dto.getRoleId());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        // user.setPassword(dto.getPassword());
        user.setBio(dto.getBio());
        user.setProfileImage(dto.getProfileImage());
        user.setCountry(dto.getCountry());
        user.setCity(dto.getCity());
        user.setRoleId(dto.getRoleId());
        user.setFcmToken(dto.getFcmToken());

        return userRepository.save(user);
    }

    private String hashPassword(String password) {
        String hashedPassword = encoder.encode(password);
        return hashedPassword;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
