package com.ecommers.product_service.repository;

import com.ecommers.product_service.entity.Category;
import com.ecommers.product_service.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c FROM Category c WHERE c.categoryId =:categoryId OR c.name =:categoryName")
    Optional<Category> findByIdOrName(@Param("categoryId") Long categoryId, @Param("categoryName") String categoryName);
    @Modifying
    @Transactional
    @Query("DELETE FROM Category c WHERE c.categoryId =:categoryId")
    void deleteCategoryById(Long categoryId);
}
