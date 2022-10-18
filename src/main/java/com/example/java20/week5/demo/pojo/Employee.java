package com.example.java20.week5.demo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    /*
        "id": 20,
        "employee_name": "Dai Rios",
        "employee_salary": 217500,
        "employee_age": 35,
        "profile_image": "
     */
    private int id;
    @JsonProperty("employee_name")
    private String name;
    @JsonProperty("employee_salary")
    private int salary;
    @JsonProperty("employee_age")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
