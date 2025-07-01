package com.hashpow.spring_backend.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "crypto_tasks")
public class CryptoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String inputText;

    private TaskStates state;

    private String outputHash;

    @NonNull
    private String userId;

    private LocalDateTime createdAt;

    public CryptoTask(){}

    public CryptoTask(String inputText, String userId) {
        this.userId = userId;
        this.inputText = inputText;
    }
}
