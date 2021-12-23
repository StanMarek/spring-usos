package com.stanmarek.springusos;

import com.stanmarek.springusos.model.Role;
import com.stanmarek.springusos.model.Status;
import com.stanmarek.springusos.model.Student;
import com.stanmarek.springusos.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringUsosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringUsosApplication.class, args);
    }

    @Bean
    CommandLineRunner run(StudentService studentService) {
        return args -> {
            Role student = new Role(null, "HEADMASTER");
            studentService.saveRole(student);
            studentService.saveRole(new Role(null, "TEACHER"));
            studentService.saveRole(new Role(null, "STUDENT"));

            studentService.saveStudent(new Student(null, "Stan", "Marek", 2000, Status.WORK_OFF, 10.0, student, "email@email.com", "password"));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
