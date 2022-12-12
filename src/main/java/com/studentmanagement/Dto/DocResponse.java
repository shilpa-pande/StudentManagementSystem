package com.studentmanagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class DocResponse {

    private String name;
    private String url;

    private String type;


    public DocResponse(String name, String url, String type) {
        this.name = name;
        this.url = url;
        this.type = type;

    }


}
