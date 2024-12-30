package com.ecommerce.user_service.service.client;

import com.ecommerce.user_service.dto.client.OrdersDTO;
import com.ecommerce.user_service.dto.client.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "product", fallback = ProductFallback.class)
public interface ProductClient {
    @GetMapping("/products")
    ResponseEntity<Set<ProductDTO>> getProductByIdOrName(@RequestParam(required = false) Long productId, @RequestParam(required = false) String productName);
}