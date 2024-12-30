package com.ecommerce.orders.repository;

import com.ecommerce.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface OrdersRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.orderId =:orderId")
    Optional<Order> findOrderById(@Param("orderId") String orderId);
    @Query("SELECT o FROM Order o WHERE o.userId =:userId")
    Optional<Set<Order>> findOrderByUserId(@Param("userId") Long userId);
}
