package com.ecommers.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private boolean isStockAvailable;
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
