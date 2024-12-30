package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.client.UserOrdersDTO;
import com.ecommerce.user_service.service.clientImpl.UserOrdersService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class UserOrdersController {
    private UserOrdersService userOrdersService;
    private static final Logger logger = LoggerFactory.getLogger(UserOrdersController.class);

    @GetMapping("/user-orders/{userId}")
    public ResponseEntity<Set<UserOrdersDTO>> getUserOrders( @PathVariable Long userId){
      return ResponseEntity.status(HttpStatus.OK).body(userOrdersService.getUserOrderDetails(userId));

    }
}
