package com.studentmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthenticationSuccessHandler successHandler;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailServiceImpl();
	}


    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return authProvider;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("admin/**").hasRole("ADMIN")
		.antMatchers("student/**").hasRole("STUDENT")
		.antMatchers("/**").permitAll()

				.and().
				formLogin().
				loginPage("/login")
		.successHandler(successHandler)
		.and().csrf().disable();
		
	}






}


