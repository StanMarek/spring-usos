package com.stanmarek.springusos.dto;

import com.stanmarek.springusos.model.Group;
import com.stanmarek.springusos.model.Student;
import com.stanmarek.springusos.model.Subject;
import lombok.Data;

import java.util.List;

@Data
public class GroupDto {

    private Long id;
    private String name;
    private Integer maxStudents;
    private List<StudentDto> students;
    private List<SubjectDto> subjects;
    private Double fill;

    public GroupDto(Group g) {
        this.id = g.getId();
        this.name = g.getName();
        this.maxStudents = g.getMaxStudents();
        for(Student student : g.getStudents()) {
            this.students.add(new StudentDto(student));
        }
        for(Subject subject : g.getSubjects()) {
            this.subjects.add(new SubjectDto(subject));
        }
        this.fill = (double) g.getStudents().size()/maxStudents;
    }

}
