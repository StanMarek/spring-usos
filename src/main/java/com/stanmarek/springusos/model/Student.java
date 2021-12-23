package com.stanmarek.springusos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private Integer yearOfBirth;
    private Status status;
    private Double points;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private String email;
    private String password;

}
