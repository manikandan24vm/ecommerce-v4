package com.ecommerce.user_service.service;
import java.util.List;

import com.ecommerce.user_service.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public User createUser(User user);

    public List<User> getAllUsers();

    public User updateUser(Long userId, User user);

    public User getUserById(Long userId);

    public void deleteUserById(Long userId);

    public UserDetails loadUserByUserName(String email);



}
