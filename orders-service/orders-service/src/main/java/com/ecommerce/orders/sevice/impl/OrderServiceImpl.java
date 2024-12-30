package com.ecommerce.orders.sevice.impl;

import com.ecommerce.orders.entity.Order;
import com.ecommerce.orders.exception.OrderNotFoundException;
import com.ecommerce.orders.exception.OrdersException;
import com.ecommerce.orders.repository.OrdersRepository;
import com.ecommerce.orders.sevice.OrderService;
import com.fasterxml.classmate.members.RawMember;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrdersRepository ordersRepository;

    @Override
    public Order placeOrder(Order order, Long userId, Long productId) {
        try {
            order.setUserId(userId);
            order.setProductId(productId);
            order.setOrderId(generateOrderId());
            order.setTotalAmount(calculateTotalAmount(order.getPrice(),order.getQuantity()));
            return ordersRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Can't place a order :" + e);
        }
    }

    @Override
    public List<Order> getAllOrders(Long userId) {
        List<Order> orders = ordersRepository.findAll();
        if (!CollectionUtils.isEmpty(orders)) {
            return orders;
        } else {
            throw new OrderNotFoundException("Orders not found.");
        }
    }

    @Override
    public Order getOrderById(String orderId) {
        Order order = ordersRepository.findOrderById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID :" + orderId));
        return order;
    }

    @Override
    public void CancelOrder(String orderId) {
        Order order = ordersRepository.findOrderById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID :" + orderId));
        try {
            ordersRepository.delete(order);
        } catch (Exception e) {
            throw new OrdersException("Can't cancel the order with ID :" + orderId);
        }
    }

    @Override
    public String generateOrderId() {
        Random random=new Random();
        int sixDigitNumber = 100000 + random.nextInt(900000);
        return "ODR"+sixDigitNumber;
    }

    @Override
    public Double calculateTotalAmount(Double price, Long quantity) {
        return (quantity==0)?price:quantity*price;
    }

    @Override
    public Set<Order> getOrderByUserId(Long userId) {
        Set<Order> orders = ordersRepository.findOrderByUserId(userId).orElseThrow(()-> new OrderNotFoundException("Orders not found for the user :"+userId));
       // Set<Order> orders= ordersRepository.findOrderByUserId(userId).stream().collect(Collectors.toSet());
        if(!CollectionUtils.isEmpty(orders)){
           return orders;
        }
        else {
            throw new OrdersException("Orders not found");
        }
    }
}
