package com.ecommerce.user_service.dto.client;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
        private Long id;
        private String orderId;
        private Long userId;
        private Long productId;
        private Double totalAmount;
        @NotNull(message = "Quantity cannot be null")
        private Long quantity;
        private Double price;
        private OrderStatus orderStatus;
}
