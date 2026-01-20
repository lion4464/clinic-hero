package com.rustambek.clinic.auth.service;

import com.rustambek.clinic.auth.dto.AuthResponse;
import com.rustambek.clinic.auth.dto.LoginRequest;
import com.rustambek.clinic.auth.dto.RegisterRequest;
import com.rustambek.clinic.exception.BadRequestException;
import com.rustambek.clinic.security.AppUserDetails;
import com.rustambek.clinic.security.JwtService;
import com.rustambek.clinic.user.entity.UserEntity;
import com.rustambek.clinic.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest req) {
        if (userRepository.findByUsernameIgnoreCase(req.username()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }
        if (userRepository.findByEmailIgnoreCase(req.email()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        String hash = passwordEncoder.encode(req.password());
        UserEntity user = new UserEntity(req.username(), req.email(), hash, req.role());
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.usernameOrEmail(), req.password())
        );

        AppUserDetails principal = (AppUserDetails) auth.getPrincipal();
        String token = jwtService.generateAccessToken(principal);
        return AuthResponse.bearer(token);
    }
}
