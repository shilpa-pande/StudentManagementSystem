package com.studentmanagement.config;
import com.studentmanagement.entity.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 7259985727812852235L;
	private Student student;




	public CustomUserDetails(Student student) {
		super();
		this.student = student;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(student.getRole().toString());



		return List.of(simpleGrantedAuthority) ;
	}

	
	public int getId()
	{
		return student.getStudentId();
	}

	
	@Override
	public String getPassword() {
		
		return student.getStudentPassword();
	}

	@Override
	public String getUsername() {
		
		return student.getStudentEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	public String getStudentName() {
		return this.student.getStudentName();
	}

}
