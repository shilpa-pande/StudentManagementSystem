package com.studentmanagement.services;

import com.studentmanagement.Dto.StudentDto;

import java.util.List;

public interface StudentService {

   StudentDto registerNewStudent(StudentDto studentDto);

    StudentDto createStudent(StudentDto studentDto, Integer classId);

    StudentDto updateStudent(StudentDto studentDto, Integer studentId);

    StudentDto getStudentById(Integer studentId);

   List<StudentDto> getAllStudents();


    List<StudentDto> getStudentByKeyword(String keyword);

    void deleteStudent(Integer studentId);
}

