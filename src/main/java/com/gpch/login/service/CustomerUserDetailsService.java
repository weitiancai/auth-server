package com.gpch.login.service;

import com.gpch.login.model.CustomerUserDetail;
import com.gpch.login.model.User;
import com.gpch.login.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username="+username);
        Optional<User> UserOptional = userRepository.findByName(username);
        UserOptional.orElseThrow(()->new UsernameNotFoundException("Username not found"));
        return UserOptional.map(CustomerUserDetail::new).get();
    }
}
