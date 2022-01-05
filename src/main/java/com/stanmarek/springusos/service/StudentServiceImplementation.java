package com.stanmarek.springusos.service;

import com.stanmarek.springusos.api.AddGrade;
import com.stanmarek.springusos.model.*;
import com.stanmarek.springusos.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StudentServiceImplementation implements StudentService {

    @Autowired
    private final StudentRepo studentRepo;

    @Autowired
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final GroupService groupService;

    @Autowired
    private final SubjectService subjectService;

    @Autowired
    private final GradeService gradeService;

    @Override
    public Student saveStudent(Student student) {
        log.info("Save new student {} to database", student.getUser().getFirstname());
        student.getUser().setPassword(passwordEncoder.encode(student.getUser().getPassword()));
        return studentRepo.save(student);
    }

    @Override
    public Student getStudent(String email) {
        log.info("Fetching student {}", email);
        User user = userService.getUser(email);
        if(user == null) {
            log.error("Student not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        return studentRepo.findByUserId(user.getId());
    }

    @Override
    public List<Student> getStudents() {
        log.info("Fetching all users");
        return studentRepo.findAll();
    }

    @Override
    public boolean deleteStudent(String email) {
        log.info("Deleting student {}", email);
        User user = userService.getUser(email);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Student s = studentRepo.findByUserId(user.getId());
        studentRepo.delete(s);
        return true;
    }

    @Override
    public Student editStudent(String email, EditStudent edit) {
        log.info("Editing student {}", email);
        User user = userService.getUser(email);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Student s = studentRepo.findByUserId(user.getId());
        if (edit.getPoints() == null) edit.setPoints(s.getPoints());
        if (edit.getStatus() == null) edit.setStatus(s.getStatus());
        s.setPoints(edit.getPoints());
        s.setStatus(edit.getStatus());
        return studentRepo.save(s);
    }

    @Override
    public Student registerStudent(String studentEmail, String className) {
        log.info("Registering student {}", studentEmail);
        User user = userService.getUser(studentEmail);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Student s = studentRepo.findByUserId(user.getId());
        Group c = groupService.getClass(className);
        if(c == null || c.getMaxStudents() == c.getStudents().size()) {
            log.error("Class not found in the database or is full. Cannot add student");
            throw new UsernameNotFoundException("Class not found in the database or is full. Cannot add student");
        } else {
            log.info("Class found in the database");
        }
        s.registerToGroup(c);
        return studentRepo.save(s);
    }

    @Override
    public Student addGrade(String email, AddGrade grade) {
        log.info("Adding grade to student {}", email);
        User user = userService.getUser(email);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Student s = studentRepo.findByUserId(user.getId());
//        Subject sub = subjectService.getSubject(grade.getSubject());
        Grade g = new Grade(null, grade.getValue(), grade.getDescription(), s);
        gradeService.addGrade(g);
        s.addGrade(g);
        Double sum = s.getGrades().stream().mapToDouble(Grade::getValue).sum();
        Double avg = sum/s.getGrades().size();
        s.setPoints(avg);
        return studentRepo.save(s);
    }

}

