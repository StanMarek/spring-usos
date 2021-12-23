package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Role;
import com.stanmarek.springusos.model.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    Role saveRole(Role role);
    Student getStudent(String email);
    List<Student> getStudents();

}
