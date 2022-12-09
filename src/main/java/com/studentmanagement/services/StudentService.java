package com.studentmanagement.services;

import com.studentmanagement.Dto.StudentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {

   StudentDto registerNewStudent(StudentDto studentDto);

    StudentDto createStudent(StudentDto studentDto, Integer classId);

    StudentDto updateStudent(StudentDto studentDto, Integer studentId);

    StudentDto getStudentById(Integer studentId);

   List<StudentDto> getAllStudents();


    List<StudentDto> getStudentByKeyword(String keyword);

    void deleteStudent(Integer studentId);


    List<StudentDto>  uploadStudentDoc(List<StudentDto> file,Integer studentId) throws IOException;


}

