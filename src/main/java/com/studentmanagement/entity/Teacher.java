package com.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="teacher")
@Getter
@Setter
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;


    private String teacherName;


    private String subject;

    @ManyToMany(mappedBy = "teachers", cascade = { CascadeType.ALL })
    @JsonIgnore
    private Set<Class> classes = new HashSet<>();


}
