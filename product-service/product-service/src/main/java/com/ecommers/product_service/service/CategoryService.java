package com.ecommers.product_service.service;

import com.ecommers.product_service.entity.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(Category category);

    public List<Category> getAllCategory();

    public Category updateCategoryById(Long categoryId, Category category);

    public void deleteCategory(Long categoryId);

    public Category getById(Long categoryId, String categoryName);

}
