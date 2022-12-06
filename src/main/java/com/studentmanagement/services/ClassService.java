package com.studentmanagement.services;

import com.studentmanagement.Dto.ClassDto;

import java.util.List;

public interface ClassService {

    ClassDto registerClass(ClassDto classDto);

    ClassDto createClass(ClassDto classDto, Integer teacherId );

    ClassDto updateClass(ClassDto classDto, Integer classId);

    ClassDto getClassById(Integer classId);

    List<ClassDto> getAllClasses();

    void deleteClass(Integer classId);

}
