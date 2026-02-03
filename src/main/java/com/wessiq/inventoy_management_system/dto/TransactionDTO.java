package com.wessiq.inventoy_management_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wessiq.inventoy_management_system.enums.TransactionStatus;
import com.wessiq.inventoy_management_system.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {

    private Long id;
    private Integer total_products;
    private BigDecimal total_price;
    private TransactionType transaction_type;
    private TransactionStatus transaction_status;
    private String description;
    private UserDTO user;
    private ProductDTO product;
    private SupplierDTO supplier;
    private LocalDateTime rdate;
    private LocalDateTime udate;
}