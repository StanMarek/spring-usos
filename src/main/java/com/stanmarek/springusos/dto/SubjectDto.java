package com.stanmarek.springusos.dto;

import com.stanmarek.springusos.model.Subject;
import lombok.Data;

@Data
public class SubjectDto {

    private Long id;
    private String name;

    public SubjectDto(Subject s) {
        this.id = s.getId();
        this.name = s.getName();
    }
}
