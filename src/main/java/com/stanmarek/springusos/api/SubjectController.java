package com.stanmarek.springusos.api;

import com.stanmarek.springusos.dto.SubjectDto;
import com.stanmarek.springusos.model.Status;
import com.stanmarek.springusos.model.Student;
import com.stanmarek.springusos.model.Subject;
import com.stanmarek.springusos.model.User;
import com.stanmarek.springusos.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subjects")
@CrossOrigin("*")
public class SubjectController {

    @Autowired
    private final SubjectService subjectService;

    @GetMapping("")
    public ResponseEntity<List<Subject>> getSubjects() {
        return ResponseEntity.ok().body(subjectService.getSubjects());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Subject> getSubject(@PathVariable final String name) {
        return ResponseEntity.ok().body(subjectService.getSubject(name));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveSubject(@RequestBody Subject subject) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/subjects/save").toUriString());

        Subject s = new Subject(null, subject.getName(), new HashSet<>());
        return ResponseEntity.created(uri).body(subjectService.saveSubject(s));
    }
}
