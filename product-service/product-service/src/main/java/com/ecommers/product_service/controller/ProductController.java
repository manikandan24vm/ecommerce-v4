package com.ecommers.product_service.controller;

import com.ecommers.product_service.dto.ErrorResponseDTO;
import com.ecommers.product_service.dto.ProductDTO;
import com.ecommers.product_service.entity.Product;
import com.ecommers.product_service.mapper.ProductMapper;
import com.ecommers.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    private Environment environment;
    @Operation(description = "create product",summary = "provides ability to create the product")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "product created successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/products/{categoryName}")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO, @PathVariable String categoryName){
        Product product= ProductMapper.toEntity(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDTO(productService.createProduct(product,categoryName)));
    }
    @Operation(description = "Retrieve all available products",summary = "provides ability to retrieve the products")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "product retrieved successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
       List<ProductDTO> productDTOList= productService.getAllProducts().stream().map(ProductMapper::toDTO).toList();
      return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }
    @Operation(description = "Get product",summary = "provides ability to retrieve the product")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "product retrieved successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/products/{productId}/{productName}")
    public ResponseEntity<ProductDTO> getProductByIdOrName(@RequestParam(required = false) Long productId,@RequestParam(required = false) String productName){
        Product product=productService.getProductByIdOrName(productId,productName);
       return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toDTO(product));
    }
    @Operation(description = "Update the product",summary = "provides ability to update the existing product")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "product updated successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId,@RequestBody @Valid ProductDTO productDTO){
        ProductDTO product= ProductMapper.toDTO(productService.updateById(productId,ProductMapper.toEntity(productDTO)));
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
    @DeleteMapping("/products/{productId}")
    @Operation(description = "Delete the product",summary = "provides ability to delete the existing product")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "product deleted successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.status(HttpStatus.OK).body("product deleted successfully with ID "+productId);
        }
        catch (Exception e){
            throw new RuntimeException("can't delete the product");

        }
    }

        @GetMapping("/app-name")
        public String getAppName() {
            return environment.getProperty("spring.application.name");
        }

}
