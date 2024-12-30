package com.ecommerce.orders.sevice.impl;

import com.ecommerce.orders.entity.Order;
import com.ecommerce.orders.entity.OrderStatus;
import com.ecommerce.orders.entity.Payment;
import com.ecommerce.orders.entity.PaymentStatus;
import com.ecommerce.orders.exception.OrderNotFoundException;
import com.ecommerce.orders.exception.OrdersException;
import com.ecommerce.orders.exception.PaymentException;
import com.ecommerce.orders.exception.PaymentNotFoundException;
import com.ecommerce.orders.repository.OrdersRepository;
import com.ecommerce.orders.repository.PaymentRepository;
import com.ecommerce.orders.sevice.OrderService;
import com.ecommerce.orders.sevice.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    private OrdersRepository ordersRepository;
    @Override
    public Payment makePayment(Payment payment, String orderId) {
        if(orderId==null){
            throw new PaymentException("order ID can't be null");
        }
       Order order= ordersRepository.findOrderById(orderId)
                .orElseThrow(()->new OrderNotFoundException("Order not found can't make the payment"));
        if(!order.getOrderStatus().equals(OrderStatus.PLACED)){
            throw new OrdersException("Order status should be 'PLACED'");
        }
        try{
            payment.setOrderId(orderId);
            payment.setAmountPaid(order.getTotalAmount());
            payment.setPaymentNumber(generatePaymentNumber());
            payment.setPaymentDate(setPaymentDate());
            payment.setPaymentStatus(PaymentStatus.PAID);
           return paymentRepository.save(payment);
        }catch (Exception e){
            throw new PaymentException("Can't make a payment");
        }
    }

    @Override
    public Payment checkPaymentStatus(String paymentNumber) {
       Payment payment= paymentRepository.checkPaymentStatus(paymentNumber)
               .orElseThrow(()-> new PaymentNotFoundException("Payment not found with Id :"+paymentNumber));
       return payment;
    }

    @Override
    public String generatePaymentNumber() {
        Random random=new Random();
        int sixDigitNumber = 100000 + random.nextInt(900000);
        return "TXN"+sixDigitNumber;
    }

    @Override
    public LocalDateTime setPaymentDate() {
        return LocalDateTime.now();
    }
}
