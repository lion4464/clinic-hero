package com.rustambek.clinic.user.controller;

import com.rustambek.clinic.security.AppUserDetails;
import com.rustambek.clinic.user.dto.UserCreateRequest;
import com.rustambek.clinic.user.dto.UserDetailDto;
import com.rustambek.clinic.user.dto.UserDto;
import com.rustambek.clinic.user.model.Role;
import com.rustambek.clinic.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController("")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/get_me")
    public UserDetailDto me(Authentication authentication) {
        AppUserDetails principal = (AppUserDetails) authentication.getPrincipal();
        return userService.toUserDetail(principal);
    }


    @GetMapping("/admin/metrics")
    @PreAuthorize("hasAuthority('ADMIN_METRICS')")
    public String metrics() {
        return "very secret metrics";
    }


    @PostMapping("/admin/users")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public UserDto createUser(@Valid @RequestBody UserCreateRequest req) {
        return userService.createUser(req, Role.USER);
    }
}
