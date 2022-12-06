package com.studentmanagement.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class ClassDto {


    private Integer classId;


    private String className;


    private String classRoom;

    private List<StudentDto> students=new ArrayList<>();


}
