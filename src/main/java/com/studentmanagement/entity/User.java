package com.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  long userId;
    private String username;
    private long mobile;
    private String email;

    private String address;

    private String password;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
