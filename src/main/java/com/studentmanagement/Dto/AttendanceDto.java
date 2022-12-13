package com.studentmanagement.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class AttendanceDto {
    private Integer attendanceId;

    private String date;

    private String attendance;
}
