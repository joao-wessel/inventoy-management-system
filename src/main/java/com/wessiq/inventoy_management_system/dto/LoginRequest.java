package com.wessiq.inventoy_management_system.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank(message = "E-mail is required") String email,
                           @NotBlank(message = "Password is required") String password) {
}