package com.studentmanagement.services.impl;

import com.studentmanagement.Dto.ClassDto;
import com.studentmanagement.entity.Class;
import com.studentmanagement.entity.Teacher;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.repository.ClassRepository;
import com.studentmanagement.repository.TeacherRepository;
import com.studentmanagement.services.ClassService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements  ClassService{


    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public ClassDto registerClass(ClassDto classDto) {
        Class aClass = this.modelMapper.map(classDto, Class.class);



        Class savedClass=this.classRepository.save(aClass);
        return this.modelMapper.map(savedClass, ClassDto.class);
    }

    @Override
    public ClassDto createClass(ClassDto classDto,Integer teacherId) {

        Teacher teacher=this.teacherRepository.findById(teacherId).orElseThrow(()->new ResourceNotFoundException("Teacher","Id",teacherId));
        Class aClass = this.modelMapper.map(classDto, Class.class);
        aClass.setTeachers(Collections.singleton(teacher));
        Class saveClasses = this.classRepository.save(aClass);
        return this.modelMapper.map(saveClasses, ClassDto.class);
    }

    @Override
    public ClassDto updateClass(ClassDto classDto, Integer classId) {
        Class aClass = this.classRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("Class", "Id", classId));

        aClass.setClassName(classDto.getClassName());
        aClass.setClassRoom(classDto.getClassRoom());
        Class updateClass= this.classRepository.save(aClass);
        return this.modelMapper.map(updateClass,ClassDto.class);

    }

    @Override
    public ClassDto getClassById(Integer classId) {
        Class aClass=this.classRepository.findById(classId).orElseThrow(()-> new ResourceNotFoundException("Class","Id",classId));
        return this.modelMapper.map(aClass, ClassDto.class);
    }

    @Override
    public List<ClassDto> getAllClasses() {
        List<Class> classes = this.classRepository.findAll();
        List<ClassDto> classDtos = classes.stream().map((classes1) -> this.modelMapper.map(classes1, ClassDto.class)).collect(Collectors.toList());
        return classDtos;
    }


    @Override
    public void deleteClass(Integer classId) {
        Class aClass=this.classRepository.findById(classId).orElseThrow(()->new ResourceNotFoundException("Class","Class id",classId));
        this.classRepository.delete(aClass);
    }
}
