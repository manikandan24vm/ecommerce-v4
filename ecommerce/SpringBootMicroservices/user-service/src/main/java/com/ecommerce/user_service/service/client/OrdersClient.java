package com.ecommerce.user_service.service.client;

import com.ecommerce.user_service.dto.client.OrdersDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@FeignClient(name="orders",fallback=OrdersFallback.class)
public interface OrdersClient {
    @GetMapping("/order/user/{userId}")
    ResponseEntity<Set<OrdersDTO>> getOrderByUserId(@PathVariable Long userId);

}
