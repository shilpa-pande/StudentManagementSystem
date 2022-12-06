package com.studentmanagement.services;

import com.studentmanagement.entity.User;
import com.studentmanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional
                .orElseThrow(()->new UsernameNotFoundException("Username is incorrect"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user.getRole().getName()));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }

}
