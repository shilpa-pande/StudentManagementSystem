package com.studentmanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="attendance")
@Getter
@Setter
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId;

    private String date;

    private String attendance;


    @ManyToOne
    private Student student;
}
