package com.studentmanagement.config;

import com.studentmanagement.entity.Student;
import com.studentmanagement.entity.Teacher;
import com.studentmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TeacherDetailsServiceImpl implements UserDetailsService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher=teacherRepository.getTeacherByEmail(username);


        if(teacher==null) {

            throw new UsernameNotFoundException("user 404");
        }
        return new CustomTeacherDetails(teacher);
    }

}

