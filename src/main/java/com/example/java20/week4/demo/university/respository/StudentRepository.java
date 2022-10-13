package com.example.java20.week4.demo.university.respository;

import com.example.java20.week4.demo.university.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, String>, StudentCustomRepo{ }
