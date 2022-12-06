package com.studentmanagement.repository;

import com.studentmanagement.entity.Class;
import com.studentmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {


    List<Teacher> findByClasses(Class aClass);
}
