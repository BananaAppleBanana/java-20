package com.example.java20.week4.demo.university.respository.impl;

import com.example.java20.week4.demo.university.domain.entity.Student;
import com.example.java20.week4.demo.university.respository.StudentCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class StudentCustomRepoImpl implements StudentCustomRepo {

    private final EntityManager entityManager;

    @Autowired
    public StudentCustomRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student insert(Student student) {
        entityManager.persist(student);
        return student;
    }
}
