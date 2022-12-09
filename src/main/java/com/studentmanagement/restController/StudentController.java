package com.studentmanagement.restController;

import com.studentmanagement.Dto.*;
import com.studentmanagement.config.ImageUtility;
import com.studentmanagement.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;



    //register new user api
    @PostMapping("/register")
    public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentDto studentDto){

        StudentDto registerNewStudent = this.studentService.registerNewStudent(studentDto);

        return new ResponseEntity<>(registerNewStudent,HttpStatus.CREATED);
   }

    //api for creating students
    @PostMapping("/class/{classId}")
    public ResponseEntity<StudentDto> createUser(@Valid @RequestBody StudentDto studentDto, @PathVariable Integer classId){
        StudentDto createStudentDto=this.studentService.createStudent(studentDto,classId);
        return new ResponseEntity<>(createStudentDto, HttpStatus.CREATED);
    }


    //update student by id
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDto> updatePost(@RequestBody StudentDto studentDto,@PathVariable Integer studentId)
    {
        StudentDto updateStudent=this.studentService.updateStudent(studentDto,studentId);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    //get students details by id
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Integer studentId){
        StudentDto studentDto=this.studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }



    //get All Students
    @GetMapping("/")

    public ResponseEntity<List<StudentDto>> getAllStudents(@RequestParam String keyword){
        if(keyword==null) {
            return ResponseEntity.ok(this.studentService.getAllStudents());
        }
        else{
            return ResponseEntity.ok(this.studentService.getStudentByKeyword(keyword));
        }
    }



    // delete post by id
    @DeleteMapping("/{studentId}")
    public ApiResponse deleteStudent(@PathVariable Integer studentId)
    {
        this.studentService.deleteStudent(studentId);
        return new ApiResponse("student is successfully deleted",true);
    }

    //upload document

    @PostMapping("/doc/{studentId}")
    public ResponseEntity<List<StudentDto>> uploadStudentDoc(@RequestParam("doc") MultipartFile[] files, @PathVariable Integer studentId) throws IOException {

        List<StudentDto> fileList =new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            byte[] bytes = ImageUtility.compressImage(file.getBytes());

            StudentDto fileModal = new StudentDto(fileName,bytes);

            // Adding file into fileList
            fileList.add(fileModal);
        }


        List<StudentDto> studentDtos = studentService.uploadStudentDoc(fileList, studentId);

        return new ResponseEntity<>(studentDtos, HttpStatus.OK);


    }



}
