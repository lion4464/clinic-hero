package com.rustambek.clinic.user.dto;

import com.rustambek.clinic.user.model.Role;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(UUID id, String username, String email, Role role, LocalDateTime createdAt) {
}
