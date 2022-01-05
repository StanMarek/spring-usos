package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Group;

import java.util.List;

public interface GroupService {
    Group getClass(String name);
    List<Group> getClasses();
    Group addClass(Group newGroup);
    Group editClass(String name, Group editGroup);

    Group addSubject(String group, String subject);
}
