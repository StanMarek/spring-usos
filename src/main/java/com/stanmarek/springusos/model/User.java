package com.stanmarek.springusos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Comparable<User>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstname;
    @NonNull
    private String lastname;

    @OneToOne
    private Role role;

    @Column(unique = true)
    @NonNull
    private String email;

    @JsonIgnore
    @NonNull
    private String password;

    @Override
    public int compareTo(User o) {
        return this.email.compareTo(o.getEmail());
    }
}
