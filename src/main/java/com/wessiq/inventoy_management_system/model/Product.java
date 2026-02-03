package com.wessiq.inventoy_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "SKU is required")
    @Column(unique = true)
    private String sku;

    @NotBlank(message = "Price is required")
    @Positive(message = "Product price must be a positive value")
    private BigDecimal price;

    @NotBlank(message = "Stock Quantity is required")
    @Min(value = 0, message = "Stock quantity must be above zero")
    private Integer stock_quantity;

    private String description;
    private String image_url;
    private LocalDateTime expiry_date;
    private final LocalDateTime rdate = LocalDateTime.now();
    private LocalDateTime udate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}