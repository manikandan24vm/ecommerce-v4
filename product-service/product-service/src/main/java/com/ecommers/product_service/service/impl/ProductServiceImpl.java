package com.ecommers.product_service.service.impl;

import com.ecommers.product_service.entity.Category;
import com.ecommers.product_service.entity.Product;
import com.ecommers.product_service.exception.CategoryNotFoundException;
import com.ecommers.product_service.exception.ProductNotFoundException;
import com.ecommers.product_service.repository.CategoryRepository;
import com.ecommers.product_service.repository.ProductRepository;
import com.ecommers.product_service.service.CategoryService;
import com.ecommers.product_service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(Product product, String categoryName) {
        Category category = categoryRepository.findByIdOrName(null, categoryName).orElseThrow(() -> new CategoryNotFoundException("Category not found with the Name :" + categoryName));
        try {
            product.setCategory(category);
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Can't create a product" + e);
        }
    }

    @Override
    public Product getProductByIdOrName(Long productId, String productName) {
        return productRepository.findByIdOrName(productId, productName).orElseThrow(()-> new ProductNotFoundException("product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateById(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with the ID :" + productId));
        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStockAvailable(product.isStockAvailable());
        return productRepository.save(existingProduct);

    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product not found with the ID" + id));
        try {
            productRepository.delete(product);
        } catch (Exception exception) {
            throw new RuntimeException("Can't delete the product.");
        }

    }
}
