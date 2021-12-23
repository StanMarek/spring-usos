package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Role;
import com.stanmarek.springusos.model.Student;
import com.stanmarek.springusos.repo.RoleRepo;
import com.stanmarek.springusos.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StudentServiceImplementation implements StudentService, UserDetailsService {

    private final StudentRepo studentRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepo.findByEmail(username);
        if(student == null) {
            log.error("Student not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(student.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), authorities);
    }

    @Override
    public Student saveStudent(Student student) {
        log.info("Save new student {} to database", student.getFirstname());
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Save new role {} to database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public Student getStudent(String email) {
        log.info("Fetching user {}", email);
        return studentRepo.findByEmail(email);
    }

    @Override
    public List<Student> getStudents() {
        log.info("Fetching all users");
        return studentRepo.findAll();
    }

}
