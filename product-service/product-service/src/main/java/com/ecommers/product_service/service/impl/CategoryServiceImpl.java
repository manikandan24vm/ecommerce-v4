package com.ecommers.product_service.service.impl;

import com.ecommers.product_service.entity.Category;
import com.ecommers.product_service.entity.Product;
import com.ecommers.product_service.exception.CategoryNotFoundException;
import com.ecommers.product_service.exception.ProductNotFoundException;
import com.ecommers.product_service.repository.CategoryRepository;
import com.ecommers.product_service.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(Category category) {
        try{
            return categoryRepository.save(category);
        }
        catch (Exception e){
            throw new RuntimeException("Can't create a category"+e);
        }
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategoryById(Long categoryId, Category category) {
        Category categoryData= categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException("Category not found with the ID :"+categoryId));
        if(categoryData != null) {
            return categoryRepository.save(category);
        }else {
            throw new CategoryNotFoundException("Product not found with the ID :"+categoryId);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        try{
            Category category= categoryRepository.findById(categoryId)
                    .orElseThrow(()-> new CategoryNotFoundException("category not found with ID :"+categoryId));
            categoryRepository.delete(category);
        }
        catch (Exception exception){
            throw new RuntimeException("Can't delete the category.");
        }
    }
    @Override
    public Category getById(Long categoryId, String categoryName) {
        return categoryRepository.findByIdOrName(categoryId,categoryName).orElseThrow(()-> new CategoryNotFoundException("Category Not found."));
    }
}
