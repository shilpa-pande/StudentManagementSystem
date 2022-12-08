package com.studentmanagement.services;

import com.studentmanagement.Dto.StudentDto;
import com.studentmanagement.Dto.UserDto;

import java.util.List;

public interface UserService {



    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUser();

    void deleteUser(Long userId);
}
