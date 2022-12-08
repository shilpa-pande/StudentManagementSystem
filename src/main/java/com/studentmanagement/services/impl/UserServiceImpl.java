package com.studentmanagement.services.impl;


import com.studentmanagement.Dto.UserDto;

import com.studentmanagement.entity.Role;
import com.studentmanagement.entity.User;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.repository.RoleRepo;
import com.studentmanagement.repository.UserRepository;
import com.studentmanagement.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;
    
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepo roleRepo;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);


        User addUser = this.userRepository.save(user);
        return this.modelMapper.map(addUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setUsername(userDto.getUsername());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getPassword());
        User updateUser= this.userRepository.save(user);
        return this.modelMapper.map(updateUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> user = this.userRepository.findAll();
        List<UserDto> studentDtos = user.stream().map((allUser) -> this.modelMapper.map(allUser, UserDto.class)).collect(Collectors.toList());
        return studentDtos;
    }


    @Override
    public void deleteUser(Long userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepository.delete(user);


    }
}
