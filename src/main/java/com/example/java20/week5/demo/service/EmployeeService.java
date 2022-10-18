package com.example.java20.week5.demo.service;

import com.example.java20.week5.demo.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EmployeeService {
    Map<Integer, List<Employee>> groupEmpByAge();
    List<Employee> getEmpAgeLargerThan(int age);
}
