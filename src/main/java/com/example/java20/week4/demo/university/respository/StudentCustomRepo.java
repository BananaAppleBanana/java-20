package com.example.java20.week4.demo.university.respository;

import com.example.java20.week4.demo.university.domain.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCustomRepo {
    Student insert(Student student);
}
