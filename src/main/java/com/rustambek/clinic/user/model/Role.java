package com.rustambek.clinic.user.model;

import java.util.Set;

public enum Role {
    USER(Set.of(Permission.USER_READ)),
    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE, Permission.ADMIN_METRICS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
