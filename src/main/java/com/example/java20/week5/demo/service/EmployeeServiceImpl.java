package com.example.java20.week5.demo.service;

import com.example.java20.week5.demo.config.EmpURLConfig;
import com.example.java20.week5.demo.pojo.Employee;
import com.example.java20.week5.demo.pojo.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<Integer, List<Employee>> groupEmpByAge() {
        EmployeeDTO employeeDTO = restTemplate.getForObject(EmpURLConfig.url, EmployeeDTO.class);
        return employeeDTO.getData()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getAge()));
    }

    @Override
    public List<Employee> getEmpAgeLargerThan(int age) {
        EmployeeDTO employeeDTO = restTemplate.getForObject(EmpURLConfig.url, EmployeeDTO.class);
        return employeeDTO.getData()
                .stream()
                .filter(e -> e.getAge() > age)
                .collect(Collectors.toList());
    }
}
