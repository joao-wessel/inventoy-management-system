package com.wessiq.inventoy_management_system.controller;

import com.wessiq.inventoy_management_system.dto.Response;
import com.wessiq.inventoy_management_system.dto.UserDTO;
import com.wessiq.inventoy_management_system.model.User;
import com.wessiq.inventoy_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Response> getUserTransactions(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserTransactions(id));
    }

    @GetMapping("/logged")
    public ResponseEntity<User> getLoggedUser() {
        return ResponseEntity.ok(userService.getLoggedUser());
    }
}