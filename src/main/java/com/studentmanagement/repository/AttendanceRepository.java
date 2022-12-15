package com.studentmanagement.repository;

import com.studentmanagement.entity.Attendance;
import com.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {

    public boolean existsByDate(String Date);

    List<Attendance> findByStudent(Student student);
}
