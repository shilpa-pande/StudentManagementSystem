package com.studentmanagement.services.impl;
import com.studentmanagement.Dto.AppConstants;
import com.studentmanagement.Dto.StudentDto;
import com.studentmanagement.Dto.TeacherDto;
import com.studentmanagement.entity.Class;
import com.studentmanagement.entity.Role;
import com.studentmanagement.entity.Student;
import com.studentmanagement.entity.Teacher;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.repository.ClassRepository;
import com.studentmanagement.repository.RoleRepo;
import com.studentmanagement.repository.TeacherRepository;
import com.studentmanagement.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private TeacherRepository teacherRepository;


    @Autowired
    private ClassRepository classRepository;


    @Autowired
    private RoleRepo roleRepo;


    @Override
    public TeacherDto registerNewTeacher(TeacherDto teacherDto) {

        Teacher teacher = this.modelMapper.map(teacherDto, Teacher.class);
        Role role = this.roleRepo.findById(Long.valueOf(AppConstants.Teacher_USER)).get();
        teacher.setRole(role);
        Teacher savedTeacher=this.teacherRepository.save(teacher);
        return this.modelMapper.map(savedTeacher,TeacherDto.class);
    }


    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = this.modelMapper.map(teacherDto, Teacher.class);
        Teacher addTeacher = this.teacherRepository.save(teacher);
        return this.modelMapper.map(addTeacher, TeacherDto.class);
    }



    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto, Integer teacherId) {
        Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Teacher", "Id", teacherId));
            teacher.setTeacherName(teacherDto.getTeacherName());
            teacher.setSubject(teacherDto.getSubject());
            teacher.setTeacherEmail(teacherDto.getTeacherEmail());
            teacher.setTeacherPassword(teacherDto.getTeacherPassword());
        Teacher updateTeacher= this.teacherRepository.save(teacher);
        return this.modelMapper.map(updateTeacher, TeacherDto.class);
    }


    @Override
    public TeacherDto getTeacherById(Integer teacherId) {
        Teacher teacher=this.teacherRepository.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException("Teacher","Id",teacherId));
        return this.modelMapper.map(teacher, TeacherDto.class);
    }


    @Override
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = this.teacherRepository.findAll();
        List<TeacherDto> teacherDtos = teachers.stream().map((teacher) -> this.modelMapper.map(teacher, TeacherDto.class)).collect(Collectors.toList());
        return teacherDtos;
    }



    @Override
    public void deleteTeacher(Integer teacherId) {
        Teacher teacher=this.teacherRepository.findById(teacherId).orElseThrow(()->new ResourceNotFoundException("Teacher","Id",teacherId));
        this.teacherRepository.delete(teacher);
    }

    @Override
    public List<TeacherDto> getTeacherByClass(Integer classId) {

        Class aClass=this.classRepository.findById(classId).orElseThrow(()->new ResourceNotFoundException("Class","Class id",classId));
        List<Teacher> teacher= teacherRepository.findByClasses(aClass);
        List<TeacherDto> teacherDtos= teacher.stream().map((teacher1) -> this.modelMapper.map(teacher1,TeacherDto.class)).collect(Collectors.toList());
        return teacherDtos;

    }


}
