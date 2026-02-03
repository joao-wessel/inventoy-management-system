package com.wessiq.inventoy_management_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO() {

    Long id
    Long product_id
    Long category_id
    Long supplier_id
    String name
    String sku,
    BigDecimal price
    Integer stock_quantity, String description,
    String img_url, LocalDateTime expiry_date, LocalDateTime rdate, LocalDateTime udate
}