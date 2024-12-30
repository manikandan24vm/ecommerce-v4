package com.ecommerce.user_service.security;

import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
public class UserServiceAuthenticationProvider implements AuthenticationProvider {
     private UserService userService;

     private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName=authentication.getName();
        String passCode=authentication.getCredentials().toString();
        UserDetails userDetails = userService.loadUserByUserName(userName);
        if (passwordEncoder.matches(passCode, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userName, passCode, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid Password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return  (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
