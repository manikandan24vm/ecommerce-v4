package com.ecommerce.orders.controller;

import com.ecommerce.orders.dto.ErrorResponseDTO;
import com.ecommerce.orders.dto.PaymentsDTO;
import com.ecommerce.orders.entity.Payment;
import com.ecommerce.orders.mapper.PaymentsMapper;
import com.ecommerce.orders.sevice.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PaymentsController {
    private PaymentService paymentService;
    @Operation(description = "make a payment",summary = "provides ability to make a payment")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "payment created successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/payment")
    public ResponseEntity<PaymentsDTO> makePayment(@RequestBody PaymentsDTO paymentsDTO, String orderId){
        Payment payment=paymentService.makePayment(PaymentsMapper.toEntity(paymentsDTO),orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(PaymentsMapper.toPaymentsDTO(payment));
    }
    @Operation(description = "check the payment",summary = "provides ability to check the payment by payment number")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "payment details retrieved successfully"),
            @ApiResponse(responseCode = "500",description = "internal server error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/payment/{paymentNumber}")
   public ResponseEntity<PaymentsDTO> checkPaymentStatus(@PathVariable String paymentNumber){
        Payment payment=paymentService.checkPaymentStatus(paymentNumber);
        return ResponseEntity.status(HttpStatus.OK).body(PaymentsMapper.toPaymentsDTO(payment));
    }
}
