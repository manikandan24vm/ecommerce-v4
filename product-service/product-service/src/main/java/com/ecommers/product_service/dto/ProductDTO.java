package com.ecommers.product_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    @NotEmpty(message = "product name cannot be empty")
    @NotNull(message = "product name cannot be null")
    private String productName;
    @NotEmpty@NotEmpty(message = "description cannot be empty")
    @NotNull(message = "description cannot be null")
    private String description;
    @NotNull(message = "price cannot be null")
    private double price;

    @NotNull
    @JsonProperty("stockAvailable")
    private boolean isStockAvailable;
}
