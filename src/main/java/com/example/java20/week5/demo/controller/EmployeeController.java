package com.example.java20.week5.demo.controller;

import com.example.java20.week5.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * url: /employee
     */
    @GetMapping
    public ResponseEntity<?> getEmpGroupByAge() {
        return new ResponseEntity<>(employeeService.groupEmpByAge(), HttpStatus.OK);
    }
    /**
     * url: /employee?age=xx
     */
    @GetMapping(params = "age")
    public ResponseEntity<?> getEmpAgeLargerThan(@RequestParam int age) {
        return new ResponseEntity<>(employeeService.getEmpAgeLargerThan(age), HttpStatus.OK);
    }
}
