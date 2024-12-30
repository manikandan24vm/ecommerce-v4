package com.ecommers.product_service.mapper;

import com.ecommers.product_service.dto.CategoryDTO;
import com.ecommers.product_service.entity.Category;

public class CategoryMapper {
    public static Category toEntity(CategoryDTO categoryDTO){
        Category category=new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
    public static CategoryDTO toDTO(Category category){
      CategoryDTO categoryDTO=new CategoryDTO();
      categoryDTO.setCategoryId(category.getCategoryId());
      categoryDTO.setName(category.getName());
      categoryDTO.setDescription(category.getDescription());
      return categoryDTO;
    }
}
