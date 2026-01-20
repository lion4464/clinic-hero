package com.rustambek.clinic.auth.dto;

import com.rustambek.clinic.user.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Size(min = 3, max = 64) String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 1, max = 5) String password,
        @NotNull Role role
) {}
