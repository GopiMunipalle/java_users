package com.CineCrew.cinema.service;

import org.springframework.stereotype.Service;

import com.CineCrew.cinema.dto.UserDto;
import com.CineCrew.cinema.models.Role;
// import com.CineCrew.cinema.models.Role;
import com.CineCrew.cinema.models.User;
import com.CineCrew.cinema.repository.RoleRepository;
import com.CineCrew.cinema.repository.UserRepository;
import com.CineCrew.cinema.utils.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashMap;
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
        System.out.println(id);
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
        user.setRoleId((long) dto.getRoleId());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBio(dto.getBio());
        user.setProfileImage(dto.getProfileImage());
        user.setCountry(dto.getCountry());
        user.setCity(dto.getCity());
        user.setRoleId((long) dto.getRoleId());
        user.setFcmToken(dto.getFcmToken());

        return userRepository.save(user);
    }

    private String hashPassword(String password) {
        String hashedPassword = encoder.encode(password);
        return hashedPassword;
    }

    public HashMap<String, Object> Login(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Role role = roleRepository.findById(user.getRoleId()).orElse(null);
        if (role == null) {
            throw new RuntimeException("Role not found");
        }

        // Generate JWT token
        String jwtToken = JwtUtil.generateToken(user.getId(), Arrays.asList(role.getName()));

        HashMap<String, Object> result = new HashMap<>();
        result.put("jwtToken", jwtToken);
        result.put("user", user);
        return result;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
