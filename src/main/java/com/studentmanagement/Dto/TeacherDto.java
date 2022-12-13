package com.studentmanagement.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class TeacherDto {

    private Integer TeacherId;


    @Size(min=4, message = "teachers name must be min of 4 characters")
    private String teacherName;


    private String subject;

    private String teacherEmail;

    private String teacherPassword;

    private Set<ClassDto> classes = new HashSet<>();
}
