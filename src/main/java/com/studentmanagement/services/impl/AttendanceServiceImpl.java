package com.studentmanagement.services.impl;

import com.studentmanagement.Dto.ApiResponse;
import com.studentmanagement.Dto.AttendanceDto;
import com.studentmanagement.entity.Attendance;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.repository.AttendanceRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.services.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public AttendanceDto createAttendance(AttendanceDto attendanceDto,Integer studentId) throws ApiResponse {
        Student student=this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student","Id",studentId));
        Attendance attendance = this.modelMapper.map(attendanceDto, Attendance.class);
        attendance.setStudent(student);
        String date = attendanceDto.getDate();
        boolean b = attendanceRepository.existsByDate(date);
        System.out.println(b);
        if(b==true){
            throw new RuntimeException("already update attendance for this date");

        }
        Attendance saveAttendance=this.attendanceRepository.save(attendance);
        return this.modelMapper.map(saveAttendance, AttendanceDto.class);

    }

    @Override
    public AttendanceDto updateAttendance(AttendanceDto attendanceDto, Integer attendanceId) {
        Attendance attendance = this.attendanceRepository.findById(attendanceId).orElseThrow(() -> new ResourceNotFoundException("Attendance", "Id", attendanceId));
        attendance.setDate(attendanceDto.getDate());
        attendance.setAttendance(attendanceDto.getAttendance());
        Attendance updateAttendance= this.attendanceRepository.save(attendance);
        return this.modelMapper.map(updateAttendance, AttendanceDto.class);
    }

    @Override
    public AttendanceDto getAttendanceById(Integer attendanceId) {
        Attendance attendance=this.attendanceRepository.findById(attendanceId).orElseThrow(()-> new ResourceNotFoundException("Attendance","Id",attendanceId));
        return this.modelMapper.map(attendance, AttendanceDto.class);
    }

    @Override
    public List<AttendanceDto> getAllAttendances() {
        List<Attendance> attendances = this.attendanceRepository.findAll();
        List<AttendanceDto> attendanceDtos = attendances.stream().map((attendance) -> this.modelMapper.map(attendance, AttendanceDto.class)).collect(Collectors.toList());
        return attendanceDtos;
    }

    @Override
    public void deleteAttendance(Integer attendanceId) {
        Attendance attendance=this.attendanceRepository.findById(attendanceId).orElseThrow(()-> new ResourceNotFoundException("Attendance","Id",attendanceId));
        this.attendanceRepository.delete(attendance);
    }

    @Override
    public List<AttendanceDto> getAttendanceByStudent(Integer studentId) {
        Student student=this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
        List<Attendance> attendances = attendanceRepository.findByStudent(student);
        List<AttendanceDto> attendanceDtos= attendances.stream().map((attendance) -> this.modelMapper.map(attendance,AttendanceDto.class)).collect(Collectors.toList());
        return attendanceDtos;

    }
}
