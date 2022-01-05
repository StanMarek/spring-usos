package com.stanmarek.springusos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * japierdole zaraz coś rozkuerwie do kurwy nędzy
 * 2h szukania blędu tylko dlatego ze gruop iest zarezerwowane w sql
 * a tak to pewnie wszystko działa nosz kurwa mać
 */
@Table(name = "group_table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Integer maxStudents;


    @JsonBackReference
    @OneToMany(mappedBy= "currentGroup")
    private Set<Student> students = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "group_subjects",
        joinColumns = @JoinColumn(name = "subject_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    public void addSubject(Subject s) {
        this.subjects.add(s);
    }
}
