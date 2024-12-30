package com.ecommerce.user_service.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    public String addressLine1;
    public String addressLine2;
    public String addressLine3;
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{6})",message = "invalid pin code")
    public String pin;
}
