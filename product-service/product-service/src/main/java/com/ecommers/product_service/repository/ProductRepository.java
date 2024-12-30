package com.ecommers.product_service.repository;

import com.ecommers.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.productId =:productId OR p.productName =:productName")
    Optional<Product> findByIdOrName(@Param("productId") Long productId, @Param("productName") String productName);
}
