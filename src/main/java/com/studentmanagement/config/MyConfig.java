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
	public UserDetailsService studentDetailsService() {
		return new StudentDetailServiceImpl();
	}

	@Bean
	public UserDetailsService teacherDetailsService() {
		return new TeacherDetailsServiceImpl();
	}




    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(studentDetailsService());
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return authProvider;
    }

	@Bean
	DaoAuthenticationProvider authenticationProviderTeacher() {
		DaoAuthenticationProvider authProviderT = new DaoAuthenticationProvider();
		authProviderT.setUserDetailsService(teacherDetailsService());
		authProviderT.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return authProviderT;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.authenticationProvider(authenticationProviderTeacher());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/student/**").hasRole("STUDENT")
				.antMatchers("/teacherView/**").hasRole("TEACHER")
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and().
				formLogin().
				loginPage("/login").permitAll()
			.successHandler(successHandler)
			.and().csrf().disable();
		
	}






}


