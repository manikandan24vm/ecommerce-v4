package com.ecommerce.user_service.dto.client;

import com.ecommerce.user_service.dto.AddressDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrdersDTO {
    private long id;
    @NotEmpty
    @NotNull
    private String firstName;

    private String lastName;
    @NotEmpty
    @NotNull
    private String password;

    @NotEmpty
    @Email(message = "invalid email address")
    private String email;

    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String phoneNumber;


    private AddressDTO address;

    private Set<OrdersDTO> orders;

    private ProductDTO product;
}
