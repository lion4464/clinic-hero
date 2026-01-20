package com.rustambek.clinic.security;

import com.rustambek.clinic.user.entity.UserEntity;
import com.rustambek.clinic.user.model.Permission;
import com.rustambek.clinic.user.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AppUserDetails implements UserDetails {

    private final UserEntity user;
    private final Set<GrantedAuthority> authorities;

    public AppUserDetails(UserEntity user) {
        this.user = user;
        Role role = user.getRole();

        Set<GrantedAuthority> perms = role.getPermissions().stream()
                .map(Permission::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        // Spring convention: role authorities start with ROLE_
        perms.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        this.authorities = perms;
    }

    public UserEntity getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
