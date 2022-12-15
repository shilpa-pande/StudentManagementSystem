package com.studentmanagement.services;

import com.studentmanagement.Dto.ApiResponse;
import com.studentmanagement.Dto.AttendanceDto;
import com.studentmanagement.Dto.TeacherDto;

import java.util.List;

public interface AttendanceService {

    AttendanceDto createAttendance(AttendanceDto attendanceDto,Integer studentId) throws ApiResponse;


    AttendanceDto updateAttendance(AttendanceDto attendanceDto, Integer attendanceId);

    AttendanceDto getAttendanceById(Integer attendanceId);

    List<AttendanceDto> getAllAttendances();

    void deleteAttendance(Integer attendanceId);


}
