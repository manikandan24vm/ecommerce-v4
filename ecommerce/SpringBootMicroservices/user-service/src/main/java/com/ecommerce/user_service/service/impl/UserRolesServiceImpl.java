package com.ecommerce.user_service.service.impl;

import com.ecommerce.user_service.entity.Role;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.exception.UserNotFoundException;
import com.ecommerce.user_service.repository.UserRolesRepository;
import com.ecommerce.user_service.service.UserRolesService;
import com.ecommerce.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRolesServiceImpl implements UserRolesService {
    private UserRolesRepository userRolesRepository;
    private UserService userService;
    @Override
    public Role createUserRole(Role role, Long userId) {
        User user= userService.getUserById(userId);
        if(user==null)
            throw new UserNotFoundException("user not found with id :"+userId);
        try{
            role.setUser(user);
            return userRolesRepository.save(role);
        }
        catch (Exception e){
            throw new RuntimeException("Can't create user role");
        }
    }
}
