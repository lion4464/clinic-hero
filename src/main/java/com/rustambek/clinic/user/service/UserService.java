package com.rustambek.clinic.user.service;

import com.rustambek.clinic.exception.BadRequestException;
import com.rustambek.clinic.security.AppUserDetails;
import com.rustambek.clinic.user.dto.UserCreateRequest;
import com.rustambek.clinic.user.dto.UserDetailDto;
import com.rustambek.clinic.user.dto.UserDto;
import com.rustambek.clinic.user.entity.UserEntity;
import com.rustambek.clinic.user.model.Role;
import com.rustambek.clinic.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserCreateRequest req, Role role) {
        if (userRepository.findByUsernameIgnoreCase(req.username()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }
        if (userRepository.findByEmailIgnoreCase(req.email()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        String hash = passwordEncoder.encode(req.password());
        UserEntity user = new UserEntity(req.username(), req.email(), hash, role);
        UserEntity saved = userRepository.save(user);
        return toDto(saved);
    }

    public UserDetailDto toUserDetail(AppUserDetails principal) {
        Set<String> auths = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return new UserDetailDto(
                principal.getUsername(),
                principal.getUser().getEmail(),
                principal.getUser().getRole().name(),
                auths
        );
    }

    public static UserDto toDto(UserEntity user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt());
    }
}
