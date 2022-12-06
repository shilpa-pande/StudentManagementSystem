package com.studentmanagement.services.impl;

import com.studentmanagement.Dto.AppConstants;
import com.studentmanagement.Dto.StudentDto;
import com.studentmanagement.entity.Class;
import com.studentmanagement.entity.Role;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.repository.ClassRepository;
import com.studentmanagement.repository.RoleRepo;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepo roleRepo;

   @Override
    public StudentDto registerNewStudent(StudentDto studentDto) {

        Student student = this.modelMapper.map(studentDto, Student.class);
        student.setStudentPassword(student.getStudentPassword());
        Role role = this.roleRepo.findById(Long.valueOf(AppConstants.Student_USER)).get();
        student.setRole(role);
        Student savedStudent=this.studentRepository.save(student);
        return this.modelMapper.map(savedStudent,StudentDto.class);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto,Integer classId) {
        Class aClass= this.classRepository.findById(classId).orElseThrow(()->new ResourceNotFoundException("Class","Class id",classId));
        Student student=this.modelMapper.map(studentDto,Student.class);
        student.setAClass(aClass);
        Student saveStudent=this.studentRepository.save(student);
        return this.modelMapper.map(saveStudent,StudentDto.class);
    }



    @Override
    public StudentDto updateStudent(StudentDto studentDto, Integer studentId) {

        Student student = this.studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
        student.setStudentName(studentDto.getStudentName());
        student.setStudentMobileNo(studentDto.getStudentMobileNo());
        student.setStudentEmail(studentDto.getStudentEmail());
        student.setStudentPassword(studentDto.getStudentPassword());
        student.setStudentAddress(studentDto.getStudentAddress());
        Student updateStudent= this.studentRepository.save(student);
        return this.modelMapper.map(updateStudent,StudentDto.class);

    }

    @Override
    public StudentDto getStudentById(Integer studentId) {
        Student student=this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
        return this.modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = this.studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream().map((student) -> this.modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
        return studentDtos;
    }



    @Override
    public void deleteStudent(Integer studentId) {

        Student student=this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student","Student id",studentId));
        this.studentRepository.delete(student);

    }


    public List<StudentDto> getStudentByKeyword(String keyword) {
       List <Student> students= studentRepository.getStudentByKeyword(keyword);
        List<StudentDto> studentDtos = students.stream().map((student) -> this.modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
       return  studentDtos;
    }
}
