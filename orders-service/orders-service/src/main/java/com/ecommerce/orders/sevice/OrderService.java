package com.ecommerce.orders.sevice;

import com.ecommerce.orders.entity.Order;

import java.util.List;
import java.util.Set;

public interface OrderService {
     Order placeOrder(Order order, Long userId, Long productId);

     List<Order> getAllOrders(Long userId);

     Order getOrderById(String orderId);

     void CancelOrder(String orderId);

     String generateOrderId();

     Double calculateTotalAmount(Double price, Long quantity);

     Set<Order> getOrderByUserId(Long userId);

}
