package com.ecommerce.user_service.service.impl;

import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.exception.UserNotFoundException;
import com.ecommerce.user_service.repository.UserServiceRepository;
import com.ecommerce.user_service.service.UserService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserServiceRepository userServiceRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        try {
            log.info("saving user...");
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            return userServiceRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("cannot create the user.." + e);
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = userServiceRepository.findAll();
        if (!CollectionUtils.isEmpty(usersList)) {
            return usersList;
        } else {
            throw new UserNotFoundException("no users found");
        }
    }

    @Override
    public User updateUser(Long userId, User user) {
        User userData = userServiceRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setEmail(user.getEmail());
        userData.setPhoneNumber(user.getPhoneNumber());
        userData.setAddress(user.getAddress());
        userData.setPassword(user.getPassword());
        return userServiceRepository.save(userData);

    }

    @Override
    public User getUserById(Long userId) {
        return userServiceRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found with a ID :" + userId));
    }

    @Override
    public void deleteUserById(Long userId) {
        User users = userServiceRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found with a ID :" + userId));
        if (users != null) {
            userServiceRepository.delete(users);
        }
    }

    @Override
    public UserDetails loadUserByUserName(String email) {
        User user = userServiceRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<GrantedAuthority> authorities = user.getRole().stream().map(authority -> new SimpleGrantedAuthority(authority.getRoleNames().name())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

}
