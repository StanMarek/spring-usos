package com.stanmarek.springusos.dto;

import com.stanmarek.springusos.model.Grade;
import com.stanmarek.springusos.model.Student;
import com.stanmarek.springusos.model.Subject;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class GradeDto {

    private Long id;
    private Double value;
    private String description;
    private SubjectDto subject;

    public GradeDto(Grade g) {
        this.id = g.getId();
        this.value = g.getValue();
        this.description = g.getDescription();
//        this.subject = new SubjectDto(g.getSubject().getName());
    }
}
