package com.ecommerce.user_service.repository;

import com.ecommerce.user_service.entity.Role;
import com.ecommerce.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<Role,Long> {
}