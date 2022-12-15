package com.studentmanagement.services;

import com.studentmanagement.Dto.StudentDto;
import com.studentmanagement.Dto.TeacherDto;
import com.studentmanagement.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface StudentService {

   StudentDto registerNewStudent(StudentDto studentDto);

    StudentDto createStudent(StudentDto studentDto, Integer classId,Integer studentId);

    StudentDto updateStudent(StudentDto studentDto, Integer studentId);

    StudentDto getStudentById(Integer studentId);

   List<StudentDto> getAllStudents();


    List<StudentDto> getStudentByKeyword(String keyword);

    void deleteStudent(Integer studentId);


    StudentDto  uploadStudentDoc( MultipartFile file,  Integer studentId) throws IOException;

    StudentDto getDoc(Integer studentId);

    Stream<Student> getAllFiles();


    List<StudentDto> getStudentByClass(Integer classId);



}

