package com.stanmarek.springusos.api;

import com.stanmarek.springusos.dto.GroupDto;
import com.stanmarek.springusos.model.Group;
import com.stanmarek.springusos.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@CrossOrigin("*")
public class ClassController {

    @Autowired
    private final GroupService classService;

    @GetMapping("")
    public ResponseEntity<List<Group>> getClasses() {
        return ResponseEntity.ok().body(classService.getClasses());
    }

    @PostMapping("/save")
    public ResponseEntity<Group> saveUser(@RequestBody Group c) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/classes/save").toUriString());
        return ResponseEntity.created(uri).body(classService.addClass(c));
    }

    @PutMapping("/edit/{name}")
    public ResponseEntity<Group> editClass(@PathVariable final String name,
                                           @RequestBody Group edit) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/students/edit").toUriString());

        return ResponseEntity.ok().body(classService.editClass(name, edit));
    }

    @PutMapping("/addsubject")
    public ResponseEntity<Group> addSubject(@RequestBody AddSubject addSubject) {
        return ResponseEntity.ok().body(classService.addSubject(addSubject.getName(), addSubject.getSubject()));
    }

}
