package com.studentmanagement.repository;

import com.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query("SELECT u FROM Student u WHERE u.studentEmail = :studentEmail")
    public Student getStudentByStudentEmail(@Param("studentEmail") String studentEmail);


    @Query("SELECT a from Student a WHERE  a.studentName LIKE %?1%"+" OR a.studentMobileNo LIKE %?1%" +" OR a.studentEmail LIKE %?1%"+" OR a.studentId LIKE %?1%" +" OR a.studentAddress LIKE %?1%")
    List<Student> getStudentByKeyword(String keyword);

}
