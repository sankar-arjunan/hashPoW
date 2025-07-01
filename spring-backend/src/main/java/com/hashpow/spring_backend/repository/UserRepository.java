package com.hashpow.spring_backend.repository;

import com.hashpow.spring_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
