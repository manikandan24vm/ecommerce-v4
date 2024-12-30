package com.ecommerce.orders.mapper;

import com.ecommerce.orders.dto.PaymentsDTO;
import com.ecommerce.orders.entity.Payment;

public class PaymentsMapper {
    public static Payment toEntity(PaymentsDTO paymentsDTO){
        Payment payment=new Payment();
        payment.setId(paymentsDTO.getId());
        payment.setPaymentMethod(paymentsDTO.getPaymentMethod());
        return payment;
    }
    public static PaymentsDTO toPaymentsDTO(Payment payments){
        PaymentsDTO paymentsDTO=new PaymentsDTO();
        paymentsDTO.setId(payments.getId());
        paymentsDTO.setPaymentNumber(payments.getPaymentNumber());
        paymentsDTO.setAmountPaid(payments.getAmountPaid());
        paymentsDTO.setPaymentStatus(payments.getPaymentStatus());
        paymentsDTO.setPaymentDate(payments.getPaymentDate());
        paymentsDTO.setPaymentMethod(payments.getPaymentMethod());
        paymentsDTO.setOrderId(payments.getOrderId());
        return paymentsDTO;
    }
}
