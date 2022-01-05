package com.stanmarek.springusos.repo;

import com.stanmarek.springusos.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
