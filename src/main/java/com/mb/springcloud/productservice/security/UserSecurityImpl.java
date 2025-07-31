package com.mb.springcloud.productservice.security;

import com.mb.springcloud.productservice.model.User;
import com.mb.springcloud.productservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserSecurityImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<User> userOpt =  userRepo.findByEmail(username);

        return userOpt.map(user -> new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        )).orElseThrow(()->
                new UsernameNotFoundException("User not found with username " + username)

        );
    }
}
