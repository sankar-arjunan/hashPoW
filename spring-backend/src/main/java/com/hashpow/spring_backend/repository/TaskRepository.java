package com.hashpow.spring_backend.repository;

import com.hashpow.spring_backend.entity.CryptoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<CryptoTask, Long> {
    Optional<List<CryptoTask>> findByUserId(String userId);
}


