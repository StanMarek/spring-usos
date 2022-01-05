package com.stanmarek.springusos;

import com.stanmarek.springusos.model.*;
import com.stanmarek.springusos.service.GroupService;
import com.stanmarek.springusos.service.StudentService;
import com.stanmarek.springusos.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class SpringUsosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringUsosApplication.class, args);
    }

    @Bean
    CommandLineRunner run(StudentService studentService,
                          UserService userService,
                          GroupService groupService) {
        return args -> {
            Role headmasterRole = new Role(null, "HEADMASTER");
            Role teacherRole = new Role(null, "TEACHER");
            Role studentRole = new Role(null, "STUDENT");
            userService.saveRole(studentRole);
            userService.saveRole(teacherRole);
            userService.saveRole(headmasterRole);

            User user = new User(
                    null,
                    "Stan",
                    "Marek",
                    headmasterRole,
                    "niezlykozak@student.agh.edu.pl",
                    "kozakhaslo"
            );
            User user2 = new User(
                    null,
                    "asdf",
                    "sdf",
                    studentRole,
                    "asdasdasdasd@student.agh.edu.pl",
                    "kozakhaslo"
            );
            User user3 = new User(
                    null,
                    "Imie",
                    "Nazwisko",
                    studentRole,
                    "denciak@student.agh.edu.pl",
                    "kozakhaslo"
            );
            User user4 = new User(
                    null,
                    "Kox",
                    "Nazwisko",
                    null,
                    "email@mail.com",
                    "kozakhaslo"
            );
            userService.saveUser(user);
            userService.saveUser(user2);
            userService.saveUser(user3);
            userService.saveUser(user4);

            Group g = new Group(null, "3A", 20, new HashSet<>(), new HashSet<>());
            groupService.addClass(g);
            studentService.saveStudent(new Student(
                    null,
                    2001,
                    Status.WORK_OFF,
                    10.0,
                    user2,
                    null,
                    new HashSet<>()
            ));
            studentService.saveStudent(new Student(
                    null,
                    200,
                    Status.WORK_OFF,
                    420.0,
                    user3,
                    null,
                    new HashSet<>()
            ));

        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
