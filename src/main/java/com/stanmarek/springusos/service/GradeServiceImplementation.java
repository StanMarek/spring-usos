package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Grade;
import com.stanmarek.springusos.repo.GradeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GradeServiceImplementation implements GradeService{

    @Autowired
    private final GradeRepo gradeRepo;

    @Override
    public Grade addGrade(Grade grade) {
        log.info("Inserting new grade");
        return gradeRepo.save(grade);
    }
}
