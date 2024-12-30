package com.ecommers.product_service.controller;

import com.ecommers.product_service.dto.CategoryDTO;
import com.ecommers.product_service.dto.ErrorResponseDTO;
import com.ecommers.product_service.entity.Category;
import com.ecommers.product_service.mapper.CategoryMapper;
import com.ecommers.product_service.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    @Operation(description = "Create Category",summary = "provides ability to create the category")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "category created successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createProduct(@RequestBody @Valid CategoryDTO categoryDTO) {
        CategoryDTO category = CategoryMapper.toDTO(categoryService.createCategory(CategoryMapper.toEntity(categoryDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @Operation(description = "Get Category",summary = "provides ability to retrieve the category")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "category retrieved successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/category/{categoryId}/{name}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long categoryId, @PathVariable String name) {
        Category category = categoryService.getById(categoryId, name);
        return ResponseEntity.status(HttpStatus.OK).body(CategoryMapper.toDTO(category));

    }

    @Operation(description = "Get Categories",summary = "provides ability to retrieve all categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "categories are retrieved successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryList = categoryService.getAllCategory().stream()
                .map(CategoryMapper::toDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
    }
    @Operation(description = "Update category",summary = "provides ability to update the category")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "category updated successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @RequestBody @Valid CategoryDTO categoryDTO) {
        Category category = categoryService.updateCategoryById(categoryId, CategoryMapper.toEntity(categoryDTO));
        return ResponseEntity.status(HttpStatus.OK).body(CategoryMapper.toDTO(category));
    }
    @DeleteMapping("/category/{categoryId}")
    @Operation(description = "Delete Category",summary = "provides ability to delete the category")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "category deleted successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }


}
