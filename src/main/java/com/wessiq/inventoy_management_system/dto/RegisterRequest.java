package com.wessiq.inventoy_management_system.dto;

import com.wessiq.inventoy_management_system.enums.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank(message = "Name is required") String name,
                              @NotBlank(message = "E-mail is required") String email,
                              @NotBlank(message = "Password is required") String password,
                              @NotBlank(message = "Phone is required") String phone,
                              UserRole role) {
}