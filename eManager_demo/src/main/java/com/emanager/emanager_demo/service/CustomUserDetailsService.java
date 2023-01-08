package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.User;
import com.emanager.emanager_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given email");
        }

        return new CustomUserDetails(user);
    }

}
