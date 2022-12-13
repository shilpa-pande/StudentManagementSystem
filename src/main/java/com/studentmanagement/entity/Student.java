package com.studentmanagement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String studentName;

    private Long studentMobileNo;


    @Column(unique = true)
    private String studentEmail;


    private String studentPassword;

    private String studentAddress;


    private String docName;

    @Lob
    private byte[] data;

    private String type;

    @ManyToOne
    private Class aClass;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @OneToMany(mappedBy="student",cascade = CascadeType.ALL)
    private List<Attendance> attendances=new ArrayList<>();

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentMobileNo=" + studentMobileNo +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", aClass=" + aClass +
                ", role=" + role +
                '}';
    }
}
