package com.studentmanagement.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="class")
@Getter
@Setter
@NoArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer classId;

    @Column(unique = true)
    private String className;

    @Column(unique = true)
    private String classRoom;

    @OneToMany(mappedBy="aClass",cascade = CascadeType.ALL)
    private List<Student> students=new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "classes_teachers",
            joinColumns = {
                    @JoinColumn(name = "class_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "teacher_id")
            }
    )
    Set<Teacher> teachers = new HashSet<Teacher>();


    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classRoom='" + classRoom + '\'' +
                '}';
    }
}
