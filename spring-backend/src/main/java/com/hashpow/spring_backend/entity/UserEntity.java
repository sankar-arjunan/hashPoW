package com.hashpow.spring_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    private String userId;
    private String password;

    private String currentToken;
}
