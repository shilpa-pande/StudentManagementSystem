package com.studentmanagement.services;

import com.studentmanagement.Dto.ClassDto;
import com.studentmanagement.Dto.StudentDto;
import com.studentmanagement.Dto.TeacherDto;

import java.util.List;

public interface TeacherService {


    TeacherDto registerNewTeacher(TeacherDto teacherDto);

    TeacherDto createTeacher( Integer teacherId, String className);


    TeacherDto updateTeacher(TeacherDto teacherDto, Integer teacherId);

    TeacherDto getTeacherById(Integer teacherId);

    List<TeacherDto> getAllTeachers();

    void deleteTeacher(Integer teacherId);

    List<TeacherDto> getTeacherByClass(Integer classId);
}
