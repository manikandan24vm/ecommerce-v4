package com.ecommerce.orders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.border.Border;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_data")
public class Order extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ord_id")
    private Long id;
    private String orderId;
    private Long userId;
    private Long productId;
    private Double totalAmount;
    private Long quantity;
    private Double price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToOne(mappedBy = "order",fetch = FetchType.EAGER,orphanRemoval = true)
    private Payment payment;

}
