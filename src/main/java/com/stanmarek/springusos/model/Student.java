package com.stanmarek.springusos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer yearOfBirth;
    private Status status;
    private Double points = 0.0;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="group_id", referencedColumnName = "id")
    private Group currentGroup;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<Grade> grades = new HashSet<>();

    public void addGrade(Grade g) {
        this.grades.add(g);
    }

    public void registerToGroup(Group c) {
        this.currentGroup = c;
    }
}
