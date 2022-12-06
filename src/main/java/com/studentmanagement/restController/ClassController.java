package com.studentmanagement.restController;


import com.studentmanagement.Dto.*;
import com.studentmanagement.services.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {


    @Autowired
   private  ClassService classService;

    @PostMapping("/")
    public ResponseEntity<ClassDto> registerClass(@Valid @RequestBody ClassDto classDto){
        ClassDto createClassDto=this.classService.registerClass(classDto);
        return new ResponseEntity<>(createClassDto, HttpStatus.CREATED);
    }


    @PostMapping("/teacher/{teacherId}")
    public ResponseEntity<ClassDto> createUser(@Valid @RequestBody ClassDto classDto, @PathVariable Integer teacherId){
        ClassDto createClassDto=this.classService.createClass(classDto,teacherId);
        return new ResponseEntity<>(createClassDto, HttpStatus.CREATED);
    }

    //update class by id
    @PutMapping("/{classId}")
    public ResponseEntity<ClassDto> updateClass(@RequestBody ClassDto classDto,@PathVariable Integer classId)
    {
        ClassDto updateClass=this.classService.updateClass(classDto,classId);
        return new ResponseEntity<>(updateClass, HttpStatus.OK);
    }

    //get class details by id
    @GetMapping("/{classId}")
    public ResponseEntity<ClassDto> getClassById(@PathVariable Integer classId){
        ClassDto classDto=this.classService.getClassById(classId);
        return new ResponseEntity<>(classDto, HttpStatus.OK);
    }

    //get All classes
    @GetMapping("/")
    public ResponseEntity<List<ClassDto>> getAllClasses(){
        return ResponseEntity.ok(this.classService.getAllClasses());
    }

    //delete class by id

    @DeleteMapping("/{classId}")
    public ApiResponse deleteStudent(@PathVariable Integer classId)
    {
        this.classService.deleteClass(classId);
        return new ApiResponse("class is successfully deleted",true);
    }




}
