package com.studentmanagement.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

    private Integer studentId;

    @NotEmpty
    @Size(min=4, message = "student must be min of 4 characters")
    private String studentName;


    private Long studentMobileNo;

    @Email(message = "email address is not valid")
    private String studentEmail;


    private String studentPassword;


    private String studentAddress;

    private String docName;

    @Lob
    private byte[] data;

    private String type;

    private List<AttendanceDto> attendances=new ArrayList<>();


    public StudentDto(String docName, byte[] data, String type) {
        this.docName = docName;
        this.data = data;
        this.type = type;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentMobileNo=" + studentMobileNo +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                '}';
    }
}
