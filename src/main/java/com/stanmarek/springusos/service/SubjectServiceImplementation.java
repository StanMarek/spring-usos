package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Subject;
import com.stanmarek.springusos.model.User;
import com.stanmarek.springusos.repo.SubjectRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SubjectServiceImplementation implements SubjectService{

    @Autowired
    private final SubjectRepo subjectRepo;

    @Override
    public Subject getSubject(String name) {
        log.info("Fetching subject {}", name);
        return subjectRepo.findByName(name);
    }

    @Override
    public List<Subject> getSubjects() {
        log.info("Fetching all subjects");
        return subjectRepo.findAll();
    }

    @Override
    public Subject saveSubject(Subject s) {
        log.info("Save new subject {} to database", s.getName());

        return subjectRepo.save(s);
    }
}
