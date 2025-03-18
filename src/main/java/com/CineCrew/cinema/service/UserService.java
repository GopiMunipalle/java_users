package com.CineCrew.cinema.service;

import org.springframework.stereotype.Service;

import com.CineCrew.cinema.models.Role;
import com.CineCrew.cinema.models.User;
import com.CineCrew.cinema.repository.RoleRepository;
import com.CineCrew.cinema.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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

    public User createUser(User user, String roleNames) {
        // Set<Role> roles = new HashSet<>();
        // for (String roleName : roleNames) {
        // Role role = roleRepository.findByName(roleName)
        // .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
        // roles.add(role);
        // }
        // user.setRoles(roleNames);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
