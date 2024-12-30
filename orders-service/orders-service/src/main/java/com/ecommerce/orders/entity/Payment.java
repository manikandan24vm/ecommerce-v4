package com.ecommerce.orders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String orderId;
    private PaymentType paymentMethod;

    private Double amountPaid;
    private PaymentStatus paymentStatus;
    private String paymentNumber;
    private LocalDateTime paymentDate;
    @OneToOne
    @JoinColumn(name = "ord_id")
    private Order order;

}
