package com.ecommerce.user_service.repository;

import com.ecommerce.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserServiceRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(@Param("email") String email);
}
