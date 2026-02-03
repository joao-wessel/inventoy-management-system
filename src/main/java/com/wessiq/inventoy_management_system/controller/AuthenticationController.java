package com.wessiq.inventoy_management_system.controller;

import com.wessiq.inventoy_management_system.dto.LoginRequest;
import com.wessiq.inventoy_management_system.dto.RegisterRequest;
import com.wessiq.inventoy_management_system.dto.Response;
import com.wessiq.inventoy_management_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(userService.loginUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }
}