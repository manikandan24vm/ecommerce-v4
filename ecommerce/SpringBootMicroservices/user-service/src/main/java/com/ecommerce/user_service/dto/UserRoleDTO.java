package com.ecommerce.user_service.dto;

import com.ecommerce.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {
    private String description;
    private RoleName roleName;
    public enum RoleName{
        ADMIN,CUSTOMER
    }

}
