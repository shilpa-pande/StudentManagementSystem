package com.studentmanagement.config;

import com.studentmanagement.entity.Student;
import com.studentmanagement.entity.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomTeacherDetails implements UserDetails {

    private Teacher teacher;




    public CustomTeacherDetails(Teacher teacher) {
        super();
        this.teacher = teacher;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(teacher.getRole().toString());
        return List.of(simpleGrantedAuthority) ;
    }

    @Override
    public String getPassword() {
        return this.teacher.getTeacherPassword();
    }

    @Override
    public String getUsername() {
        return this.teacher.getTeacherEmail();
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
}
