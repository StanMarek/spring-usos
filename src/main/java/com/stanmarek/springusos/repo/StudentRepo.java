package com.stanmarek.springusos.repo;

import com.stanmarek.springusos.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {

    Student findByUserId(Long id);
}
