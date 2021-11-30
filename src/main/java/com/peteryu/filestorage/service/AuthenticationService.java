package com.peteryu.filestorage.service;

import com.peteryu.filestorage.mapper.UserMapper;
import com.peteryu.filestorage.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private UserMapper userMapper;
    private HashService hashService;

    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String plainPassword = authentication.getCredentials().toString();

        User user = userMapper.getUser(username);
        if (user != null) {
            String hashedPassword = hashService.getHashedValue(plainPassword,user.getSalt());
            if (user.getPassWord().equals(hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, hashedPassword, new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

//    public String getUserName(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return auth.getName();
//    }
}

