package com.studentmanagement.repository;

import com.studentmanagement.entity.Attendance;
import com.studentmanagement.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
}
