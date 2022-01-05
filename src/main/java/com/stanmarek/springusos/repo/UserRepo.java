package com.stanmarek.springusos.repo;

import com.stanmarek.springusos.model.Role;
import com.stanmarek.springusos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
