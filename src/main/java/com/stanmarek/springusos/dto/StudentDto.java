package com.stanmarek.springusos.dto;

import com.stanmarek.springusos.model.Grade;
import com.stanmarek.springusos.model.Group;
import com.stanmarek.springusos.model.Status;
import com.stanmarek.springusos.model.Student;
import lombok.Data;
import java.util.List;

@Data
public class StudentDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer yearOfBirth;
    private Double points;
    private Status status;
    private Group group;
    private List<GradeDto> grades;

    public StudentDto(Student s) {
        this.id = s.getId();
        this.firstname = s.getUser().getFirstname();
        this.lastname = s.getUser().getLastname();
        this.email = s.getUser().getEmail();
        this.yearOfBirth = s.getYearOfBirth();
        this.points = s.getPoints();
        this.status = s.getStatus();
        this.group = s.getCurrentGroup();
        for(Grade grade : s.getGrades()) {
            this.grades.add(new GradeDto(grade));
        }
    }

}
