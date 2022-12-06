package com.studentmanagement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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

    @ManyToOne
    private Class aClass;
//
//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name="student_role",
//            joinColumns = @JoinColumn(name = "student",referencedColumnName = "studentId"),
//            inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "roleId"))
//    private Set<Role> roles=new HashSet<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

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
