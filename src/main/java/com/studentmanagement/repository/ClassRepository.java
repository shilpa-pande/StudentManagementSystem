package com.studentmanagement.repository;

import com.studentmanagement.entity.Class;
import com.studentmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClassRepository extends JpaRepository<Class,Integer> {


    List<Class> findByTeachers(Teacher teacher);

    @Query(value = "select u from Class u where u.className = :className")
    public Class getClassName(@Param("className") String className);
}
