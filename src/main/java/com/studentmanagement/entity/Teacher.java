package com.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String teacherEmail;


    private String teacherPassword;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @ManyToMany(mappedBy = "teachers", cascade = { CascadeType.ALL })
    @JsonIgnore
    private Set<Class> classes = new HashSet<>();


    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", subject='" + subject + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", teacherPassword='" + teacherPassword + '\'' +
                ", role=" + role +

                ", classes=" + classes +
                '}';
    }
}
