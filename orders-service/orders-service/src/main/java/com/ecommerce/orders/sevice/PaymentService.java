package com.ecommerce.orders.sevice;

import com.ecommerce.orders.entity.Payment;

import java.time.LocalDateTime;

public interface PaymentService {

    Payment makePayment(Payment payment,String orderId);

    Payment checkPaymentStatus(String paymentNumber);

    String generatePaymentNumber();

    LocalDateTime setPaymentDate();

}
