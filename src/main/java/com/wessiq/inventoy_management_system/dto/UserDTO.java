package com.wessiq.inventoy_management_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wessiq.inventoy_management_system.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String name;
    private String email;

    @JsonIgnore
    private String password;

    private String phone_number;
    private UserRole role;
    private List<TransactionDTO> transactions;
    private LocalDateTime rdate;
}