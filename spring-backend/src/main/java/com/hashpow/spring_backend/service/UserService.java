package com.hashpow.spring_backend.service;

import com.hashpow.spring_backend.entity.UserEntity;
import org.apache.catalina.User;

public interface UserService {
    UserEntity authenticate(UserEntity user);
    UserEntity logout(String userId);
}
