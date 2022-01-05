package com.stanmarek.springusos.service;

import com.stanmarek.springusos.api.AddGrade;
import com.stanmarek.springusos.model.Role;
import com.stanmarek.springusos.model.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    Student getStudent(String email);
    List<Student> getStudents();
    boolean deleteStudent(String email);
    Student editStudent(String email, EditStudent edit);

    Student registerStudent(String studentEmail, String className);

    Student addGrade(String email, AddGrade grade);
}
