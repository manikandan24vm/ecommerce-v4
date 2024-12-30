package com.ecommerce.user_service.entity;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {
    public String addressLine1;
    public String addressLine2;
    public String addressLine3;
    public String pin;
}
