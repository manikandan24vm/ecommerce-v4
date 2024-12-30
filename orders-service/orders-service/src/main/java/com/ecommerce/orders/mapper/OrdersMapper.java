package com.ecommerce.orders.mapper;

import com.ecommerce.orders.dto.OrdersDTO;
import com.ecommerce.orders.entity.Order;

public class OrdersMapper {

    public static Order toEntity(OrdersDTO ordersDTO){
        Order order=new Order();
        order.setOrderId(ordersDTO.getOrderId());
        order.setOrderStatus(ordersDTO.getOrderStatus());
        order.setPrice(ordersDTO.getPrice());
        order.setQuantity(ordersDTO.getQuantity());
        order.setTotalAmount(ordersDTO.getTotalAmount());
        return order;
    }
    public static  OrdersDTO ordersDTO(Order order){
        OrdersDTO ordersDTO=new OrdersDTO();
        ordersDTO.setId(order.getId());
        ordersDTO.setProductId(order.getProductId());
        ordersDTO.setUserId(order.getUserId());
        ordersDTO.setOrderId(order.getOrderId());
        ordersDTO.setOrderStatus(order.getOrderStatus());
        ordersDTO.setPrice(order.getPrice());
        ordersDTO.setQuantity(order.getQuantity());
        ordersDTO.setTotalAmount(order.getTotalAmount());
        return ordersDTO;
    }
}
