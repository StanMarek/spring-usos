package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Role;
import com.stanmarek.springusos.model.Student;
import com.stanmarek.springusos.model.User;
import com.stanmarek.springusos.repo.RoleRepo;
import com.stanmarek.springusos.repo.StudentRepo;
import com.stanmarek.springusos.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Save new user {} to database", user.getFirstname());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Save new role {} to database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public User getUser(String email) {
        log.info("Fetching user {}", email);
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public void addRoleToUser(String email, String role) {
        log.info("Adding role {} to user {}", role, email);
        User user = userRepo.findByEmail(email);
        if(user == null) {
            log.error("Student not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Role _role = roleRepo.findByName(role);
        user.setRole(_role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user == null) {
            log.error("Student not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
