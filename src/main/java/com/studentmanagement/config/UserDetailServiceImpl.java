package com.studentmanagement.config;

import com.studentmanagement.entity.User;
import com.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


		User user=userRepository.getUserByEmail(email);


		if(user==null) {

			throw new UsernameNotFoundException("user 404");
		}
		return new CustomUserDetails(user);
	}


}
