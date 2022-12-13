package com.studentmanagement.restController;

import com.studentmanagement.Dto.ApiResponse;
import com.studentmanagement.Dto.StudentDto;
import com.studentmanagement.Dto.TeacherDto;
import com.studentmanagement.services.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @PostMapping("/register")
    public ResponseEntity<TeacherDto> registerTeacher(@RequestBody TeacherDto teacherDto) {
        TeacherDto registerNewTeacher = this.teacherService.registerNewTeacher(teacherDto);
        return new ResponseEntity<>(registerNewTeacher, HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<TeacherDto> createCategory(@Valid @RequestBody TeacherDto teacherDto){
        TeacherDto teacher = this.teacherService.createTeacher(teacherDto);
        return new ResponseEntity<>(teacher, CREATED);
    }

    //update teacher by id
    @PutMapping("/{teacherId}")
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable Integer teacherId)
    {
        TeacherDto updateTeacher=this.teacherService.updateTeacher(teacherDto,teacherId);
        return new ResponseEntity<>(updateTeacher, HttpStatus.OK);
    }

    //get teacher details by id
    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Integer teacherId){
        TeacherDto teacherDto=this.teacherService.getTeacherById(teacherId);
        return new ResponseEntity<>(teacherDto, HttpStatus.OK);
    }


    //get All teachers
    @GetMapping("/")
    public ResponseEntity<List<TeacherDto>> getAllTeachers(){
        return ResponseEntity.ok(this.teacherService.getAllTeachers());
    }

    //delete teacher by id
    @DeleteMapping("/{teacherId}")
    public ApiResponse deleteTeacher(@PathVariable Integer teacherId)
    {
        this.teacherService.deleteTeacher(teacherId);
        return new ApiResponse("teacher is successfully deleted",true);
    }


    //get teachers by class
    @GetMapping("/class/{classId}/")
    public ResponseEntity <List<TeacherDto>> getTeacherByClass(@PathVariable Integer classId){
        List<TeacherDto> teachers=this.teacherService.getTeacherByClass(classId);
        return new ResponseEntity<>(teachers, HttpStatus.OK);

    }






}
