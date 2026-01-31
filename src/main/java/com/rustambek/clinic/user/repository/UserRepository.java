package com.rustambek.clinic.user.repository;

import com.rustambek.clinic.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUsernameIgnoreCase(String username);
    Optional<UserEntity> findByEmailIgnoreCase(String email);
}
