package com.rustambek.clinic.specification;

import com.rustambek.clinic.user.entity.UserEntity;
import com.rustambek.clinic.user.model.Role;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<UserEntity> roleEq(Role role) {
        if (role == null) return null;
        return (root, query, cb) -> cb.equal(root.get("role"), role);
    }
}
