package com.studentmanagement.config;

import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String studentEmail) throws UsernameNotFoundException {


		Student student=studentRepository.getStudentByStudentEmail(studentEmail);


		if(student==null) {

			throw new UsernameNotFoundException("user 404");
		}
		return new CustomUserDetails(student);
	}


}
