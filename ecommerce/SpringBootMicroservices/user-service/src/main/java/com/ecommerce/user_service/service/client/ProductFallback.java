package com.ecommerce.user_service.service.client;

import com.ecommerce.user_service.dto.client.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class ProductFallback implements ProductClient{
    @Override
    public ResponseEntity<Set<ProductDTO>> getProductByIdOrName(Long productId, String productName) {
        return null;
    }
}
