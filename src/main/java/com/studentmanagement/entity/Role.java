package com.studentmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder

public class Role {


    @Id
    private long id;
    private String name;

}
