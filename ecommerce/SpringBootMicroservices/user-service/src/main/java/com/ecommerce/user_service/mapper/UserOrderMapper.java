package com.ecommerce.user_service.mapper;

import com.ecommerce.user_service.dto.UserDTO;
import com.ecommerce.user_service.dto.client.OrdersDTO;
import com.ecommerce.user_service.dto.client.ProductDTO;
import com.ecommerce.user_service.dto.client.UserOrdersDTO;

import java.util.Set;

public class UserOrderMapper {


    public static UserOrdersDTO convertTOUserOrderDTO(UserDTO userDTO, Set<OrdersDTO> ordersDTO, ProductDTO productDTO) {
        UserOrdersDTO userOrdersDTO = new UserOrdersDTO();
        userOrdersDTO.setId(userDTO.getId());
        userOrdersDTO.setFirstName(userDTO.getFirstName());
        userOrdersDTO.setLastName(userDTO.getLastName());
        userOrdersDTO.setAddress(userDTO.getAddress());
        userOrdersDTO.setPhoneNumber(userDTO.getPhoneNumber());
        userOrdersDTO.setOrders(ordersDTO);
        userOrdersDTO.setProduct(productDTO);
        return userOrdersDTO;
    }


}
