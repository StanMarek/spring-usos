package com.stanmarek.springusos.repo;

import com.stanmarek.springusos.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepo extends JpaRepository<Grade, Long> {
}
