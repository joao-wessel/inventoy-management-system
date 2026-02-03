package com.wessiq.inventoy_management_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TransactionRequest(@Positive(message = "Product is required") Long product_id,
                                 @Positive(message = "Quantity is required") Integer quantity, Long supplier_id,
                                 String note) {
}