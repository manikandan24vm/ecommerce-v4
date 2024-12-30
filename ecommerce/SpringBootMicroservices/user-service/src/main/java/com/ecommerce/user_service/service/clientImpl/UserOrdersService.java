package com.ecommerce.user_service.service.clientImpl;

import com.ecommerce.user_service.dto.client.OrdersDTO;
import com.ecommerce.user_service.dto.client.ProductDTO;
import com.ecommerce.user_service.dto.client.UserOrdersDTO;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.exception.UserNotFoundException;
import com.ecommerce.user_service.mapper.UserDtoMapper;
import com.ecommerce.user_service.mapper.UserOrderMapper;
import com.ecommerce.user_service.repository.UserServiceRepository;
import com.ecommerce.user_service.service.client.OrdersClient;
import com.ecommerce.user_service.service.client.ProductClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserOrdersService {

    private static final Logger log = LoggerFactory.getLogger(UserOrdersService.class);
    private UserServiceRepository userServiceRepository;
    private OrdersClient ordersClient;
    private ProductClient productClient;

    public Set<UserOrdersDTO> getUserOrderDetails(Long userId) {
        User user = userServiceRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not found with id:" + userId));
        ResponseEntity<Set<OrdersDTO>> orders = ordersClient.getOrderByUserId(userId);
        Set<UserOrdersDTO> userOrdersDTOS = new HashSet<>();
        Set<OrdersDTO> ordersDto = Objects.requireNonNull(orders.getBody()).stream()
                .filter(order -> order.getUserId().equals(userId)).collect(Collectors.toSet());
        ordersDto.forEach(order -> {
            Long productId = order.getProductId();
            log.info("productId :{}", productId);
            if (productId != null) {
                ResponseEntity<Set<ProductDTO>> products = productClient.getProductByIdOrName(productId, null);
                log.info("response :{}", products.getBody());
                Objects.requireNonNull(products.getBody()).forEach(product -> {
                    userOrdersDTOS.add(UserOrderMapper.convertTOUserOrderDTO(UserDtoMapper.entityToDto(user), ordersDto, product));
                });
            }
        });
        return userOrdersDTOS;
    }
}
