package com.amvatui.microservice.imagesmicroservice.security.service;

import com.amvatui.microservice.imagesmicroservice.mapper.UserMapper;
import com.amvatui.microservice.imagesmicroservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AuthService userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(UserMapper.INSTANCE::fromDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
