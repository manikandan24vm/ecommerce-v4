package com.ecommerce.user_service.dto.client;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO implements Serializable {
    private Long productId;

    private String productName;

    private String description;

    private double price;
    @JsonProperty("isStockAvailable")
    private boolean isStockAvailable;

    private boolean stockAvailable;

}
