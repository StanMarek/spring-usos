package com.stanmarek.springusos.api;


import com.stanmarek.springusos.dto.StudentDto;
import com.stanmarek.springusos.model.Status;
import com.stanmarek.springusos.model.Student;
import com.stanmarek.springusos.model.User;
import com.stanmarek.springusos.service.EditStudent;
import com.stanmarek.springusos.service.StudentService;
import com.stanmarek.springusos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<Student>> getStudents() {

        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentCreate studentCreate) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/students/save").toUriString());
        User user = userService.getUser(studentCreate.getEmail());
        Student student = new Student(null, studentCreate.getYearOfBirth(), Status.APPROVED, 0.0, user, null, new HashSet<>());
        return ResponseEntity.created(uri).body(studentService.saveStudent(student));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteStudent(@PathVariable final String email) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/students/delete").toUriString());
        return ResponseEntity.ok().body(studentService.deleteStudent(email));
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<Student> editStudent(@PathVariable final String email,
                                               @RequestBody EditStudent edit) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/students/edit").toUriString());

        return ResponseEntity.ok().body(studentService.editStudent(email, edit));
    }

    @PutMapping("/grade/{email}")
    public ResponseEntity<?> addGrade(@PathVariable final String email,
                                      @RequestBody AddGrade grade) {
        return ResponseEntity.ok().body(studentService.addGrade(email, grade));
    }

    @PutMapping("/register")
    public ResponseEntity<?> registerStudentToClass(@RequestBody RegisterStudent register){
        return ResponseEntity.ok().body(studentService.registerStudent(register.getStudent(), register.getClassName()));
    }

}
