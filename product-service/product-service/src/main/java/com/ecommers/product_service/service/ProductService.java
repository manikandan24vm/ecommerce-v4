package com.ecommers.product_service.service;

import com.ecommers.product_service.entity.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product product, String categoryName);

    public Product getProductByIdOrName(Long productId, String productName);

    public List<Product> getAllProducts();

    public Product updateById(Long productId, Product product);

    public void deleteProductById(Long id);

}
