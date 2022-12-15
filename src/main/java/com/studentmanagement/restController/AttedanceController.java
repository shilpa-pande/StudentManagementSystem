package com.studentmanagement.restController;

import com.studentmanagement.Dto.ApiResponse;
import com.studentmanagement.Dto.AttendanceDto;
import com.studentmanagement.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttedanceController {

    @Autowired
    AttendanceService attendanceService;


    //apply attendance of student by studentId
    @PostMapping("/{studentId}")
    public ResponseEntity<AttendanceDto> createAttendance( @RequestBody AttendanceDto attendanceDto, @PathVariable Integer studentId) throws ApiResponse {
        AttendanceDto createAttendance = this.attendanceService.createAttendance(attendanceDto,studentId);
        return new ResponseEntity<>(createAttendance, HttpStatus.CREATED);
    }


    //update attendance by attendanceId
    @PutMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDto> updateAttendance(@RequestBody AttendanceDto attendanceDto, @PathVariable Integer attendanceId)
    {
        AttendanceDto updateAttendance=this.attendanceService.updateAttendance(attendanceDto,attendanceId);
        return new ResponseEntity<>(updateAttendance, HttpStatus.OK);
    }

    //get attendance by id
    @GetMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable Integer attendanceId){
        AttendanceDto attendanceDto=this.attendanceService.getAttendanceById(attendanceId);
        return new ResponseEntity<>(attendanceDto, HttpStatus.OK);
    }


    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByStudent(@PathVariable Integer studentId){
        List<AttendanceDto> attendanceDto=  this.attendanceService.getAttendanceByStudent(studentId);
        return new ResponseEntity<>(attendanceDto, HttpStatus.OK);
    }

    //get all attendance
    @GetMapping("/")
    public ResponseEntity<List<AttendanceDto>> getAllAttendance(){
        return ResponseEntity.ok(this.attendanceService.getAllAttendances());
    }

    //delete attendence by id
    @DeleteMapping("/{attendanceId}")
    public ApiResponse deleteAttendance(@PathVariable Integer attendanceId)
    {
        this.attendanceService.deleteAttendance(attendanceId);
        return new ApiResponse("attendance is successfully deleted",true);
    }





}
