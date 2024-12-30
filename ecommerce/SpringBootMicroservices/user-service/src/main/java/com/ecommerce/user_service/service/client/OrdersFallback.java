package com.ecommerce.user_service.service.client;

import com.ecommerce.user_service.dto.client.OrdersDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class OrdersFallback implements OrdersClient{
    @Override
    public ResponseEntity<Set<OrdersDTO>> getOrderByUserId(Long userId) {
        return null;
    }
}
