package com.stanmarek.springusos.repo;

import com.stanmarek.springusos.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
}
