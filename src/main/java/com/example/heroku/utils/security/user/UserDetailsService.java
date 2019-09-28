package com.example.heroku.utils.security.user;

import com.example.heroku.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userService;

    public UserDetailsService (UserRepository userRepository){
        this.userService = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userService.getUserByEmail(s);
    }
}
