package com.studentmanagement.restController;

import com.studentmanagement.Dto.ApiResponse;
import com.studentmanagement.Dto.StudentDto;
import com.studentmanagement.Dto.TeacherDto;
import com.studentmanagement.Dto.UserDto;
import com.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/")
    public ResponseEntity<UserDto> registerStudent(@RequestBody UserDto userDto){


        UserDto registerUser = this.userService.createUser(userDto);

        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updatePost(@RequestBody UserDto userDto,@PathVariable Long userId)
    {
        UserDto updateUser=this.userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        UserDto userDto=this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @DeleteMapping("/{userId}")
    public ApiResponse deleteUser(@PathVariable Long userId)
    {
        this.userService.deleteUser(userId);
        return new ApiResponse("user is successfully deleted",true);
    }



}
