package com.rustambek.clinic.user.dto;

import java.util.Set;

public record UserDetailDto(String username, String email, String role, Set<String> authorities) {
}
