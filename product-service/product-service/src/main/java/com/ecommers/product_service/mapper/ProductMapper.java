package com.ecommers.product_service.mapper;

import com.ecommers.product_service.dto.ProductDTO;
import com.ecommers.product_service.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductDTO productDTO){
        Product product=new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockAvailable(productDTO.isStockAvailable());
       return product;
    }

    public static ProductDTO toDTO(Product product){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStockAvailable(product.isStockAvailable());
        return productDTO;
    }
}
