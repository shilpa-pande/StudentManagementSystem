package com.studentmanagement.repository;

import com.studentmanagement.entity.Class;
import com.studentmanagement.entity.Student;
import com.studentmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    @Query("SELECT t FROM Teacher t WHERE t.teacherEmail = :teacherEmail")
    public Teacher getTeacherByEmail(@Param("teacherEmail") String teacherEmail);



    List<Teacher> findByClasses(Class aClass);
}
