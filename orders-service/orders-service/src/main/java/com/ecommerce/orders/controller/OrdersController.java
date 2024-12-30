package com.ecommerce.orders.controller;

import com.ecommerce.orders.dto.ErrorResponseDTO;
import com.ecommerce.orders.dto.OrdersDTO;
import com.ecommerce.orders.entity.Order;
import com.ecommerce.orders.mapper.OrdersMapper;
import com.ecommerce.orders.sevice.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class OrdersController {
    private OrderService orderService;
    @Operation(description = "place a order",summary = "provides ability to create a order")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "order placed successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/order/{userId}/{productId}")
    public ResponseEntity<OrdersDTO> placeOrder(@RequestBody @Valid OrdersDTO ordersDTO, @PathVariable Long userId, @PathVariable Long productId){
        Order orderData= orderService.placeOrder(OrdersMapper.toEntity(ordersDTO),userId,productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrdersMapper.ordersDTO(orderData));
    }

    @Operation(description = "Get all orders",summary = "provides ability to retrieve the orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "orders are retrieved successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/order/{userId}/{productId}")
    public ResponseEntity<List<OrdersDTO>> getAllOrders(@PathVariable Long userId){
       List<OrdersDTO> orders= orderService.getAllOrders(userId)
                .stream()
                .map(OrdersMapper::ordersDTO)
                .toList();
       return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
    @Operation(description = "Get all orders",summary = "provides ability to retrieve the orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "orders are retrieved successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/order/user/{userId}")
    public ResponseEntity<Set<OrdersDTO>> getOrderByUserId(@PathVariable Long userId){
        Set<OrdersDTO> orders= orderService.getOrderByUserId(userId)
                .stream()
                .map(OrdersMapper::ordersDTO)
                .collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @Operation(description = "get orders by id",summary = "provides ability to retrieve the order")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "order retrieved successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable String orderId){
        Order order= orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(OrdersMapper.ordersDTO(order));
    }

    @Operation(description = "cancel the order",summary = "provides ability to cancel the order")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "order cancelled successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> CancelOrder(@PathVariable String orderId){
        orderService.CancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Order cancelled successfully");

    }

}
