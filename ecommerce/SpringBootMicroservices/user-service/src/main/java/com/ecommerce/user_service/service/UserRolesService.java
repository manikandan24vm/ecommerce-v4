package com.ecommerce.user_service.service;

import com.ecommerce.user_service.entity.Role;

public interface UserRolesService {

    public Role createUserRole(Role role, Long userId);
}
