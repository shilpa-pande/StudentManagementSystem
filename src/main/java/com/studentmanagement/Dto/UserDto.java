package com.studentmanagement.Dto;


import com.studentmanagement.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private  long userId;
    private String username;
    private long mobile;
    private String email;

    private String address;
    private String password;
    private String role;


}
