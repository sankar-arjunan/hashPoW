package com.hashpow.spring_backend.serviceimpl;

import com.hashpow.spring_backend.entity.UserEntity;
import com.hashpow.spring_backend.repository.UserRepository;
import com.hashpow.spring_backend.security.JwtUtil;
import com.hashpow.spring_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserEntity authenticate(UserEntity user) {
        if (user == null) return null;
        Optional<UserEntity> userEntityOpt = userRepository.findById(user.getUserId());

        UserEntity userEntity;
        if (userEntityOpt.isEmpty()) {
            userEntity = new UserEntity();
            userEntity.setUserId(user.getUserId());
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            userEntity = userEntityOpt.get();
            if (!passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
                return null;
            }
        }
        userEntity.setCurrentToken(jwtUtil.generateToken(user.getUserId()));
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity logout(String userId) {
        if (userId.isEmpty()) return null;
        Optional<UserEntity> userEntityOpt = userRepository.findById(userId);
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            userEntity.setCurrentToken(null);
            userRepository.save(userEntity);
            return userEntity;
        }
        return null;
    }

}

