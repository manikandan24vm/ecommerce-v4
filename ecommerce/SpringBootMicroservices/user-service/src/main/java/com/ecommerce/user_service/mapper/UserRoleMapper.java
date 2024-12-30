package com.ecommerce.user_service.mapper;

import com.ecommerce.user_service.dto.UserRoleDTO;
import com.ecommerce.user_service.entity.Role;

public class UserRoleMapper {
    public static Role toRole(UserRoleDTO roleDTO){
        Role role=new Role();
        role.setRoleNames(Role.RoleName.valueOf(roleDTO.getRoleName().name()));
        role.setDescription(roleDTO.getDescription());
        return role;
    }
}
