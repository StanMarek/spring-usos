package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Role;
import com.stanmarek.springusos.model.Student;
import com.stanmarek.springusos.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    User getUser(String email);
    List<User> getUsers();
    void addRoleToUser(String email, String role);
}
