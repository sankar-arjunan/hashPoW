package com.hashpow.spring_backend.controller;

import com.hashpow.spring_backend.entity.UserEntity;
import com.hashpow.spring_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "HELLO WORLD";
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserEntity user) {
        UserEntity userEntity = userService.authenticate(user);
        if(userEntity != null) return ResponseEntity.ok(userEntity.getCurrentToken());
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpRequest) {
        String userId = (String) httpRequest.getAttribute("userId");
        UserEntity userEntity = userService.logout(userId);
        if(userEntity != null) return ResponseEntity.ok("Logout successful");
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

}
