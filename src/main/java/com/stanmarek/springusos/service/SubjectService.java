package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Subject;

import java.util.List;

public interface SubjectService {

    Subject getSubject(String name);

    List<Subject> getSubjects();

    Subject saveSubject(Subject s);
}
