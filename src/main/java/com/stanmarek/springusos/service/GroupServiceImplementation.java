package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Group;
import com.stanmarek.springusos.model.Subject;
import com.stanmarek.springusos.repo.GroupRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GroupServiceImplementation implements GroupService {

    @Autowired
    private final GroupRepo groupRepo;

    @Autowired
    private final SubjectService subjectService;

    @Override
    public Group getClass(String name) {
        log.info("Fetching class {}", name);

        return groupRepo.findByName(name);
    }

    @Override
    public List<Group> getClasses() {
        log.info("Fetching all classes");
        return groupRepo.findAll();
    }

    @Override
    public Group addClass(Group newGroup) {
        log.info("Inserting new class");
        return groupRepo.save(newGroup);
    }

    @Override
    public Group editClass(String name, Group editGroup) {
        log.info("Editing class {}", name);
        Group c = groupRepo.findByName(name);
        if(c == null) {
            log.error("Class not found in the database");
            throw new UsernameNotFoundException("Class not found in the database");
        } else {
            log.info("Class found in the database");
        }

        if (editGroup.getName() == null) editGroup.setName(c.getName());
        if (editGroup.getMaxStudents() == null) editGroup.setMaxStudents(c.getMaxStudents());
        c.setName(editGroup.getName());
        c.setMaxStudents(editGroup.getMaxStudents());
        return groupRepo.save(c);
    }

    @Override
    public Group addSubject(String group, String subject) {
        Subject s = subjectService.getSubject(subject);
        if(s == null) {
            log.error("Subject not found in the database");
            throw new UsernameNotFoundException("Subject not found in the database");
        } else {
            log.info("Subject found in the database");
        }

        Group g = groupRepo.findByName(group);
        if(g == null) {
            log.error("Group not found in the database");
            throw new UsernameNotFoundException("Group not found in the database");
        } else {
            log.info("Group found in the database");
        }
        g.addSubject(s);
        return groupRepo.save(g);
    }
}
