package com.example.java20.week4.demo.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityApplication {
    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }
}

/**
 *  Student -> CRUD
 *  table : Student(id, name)
 *  1. create student
 *      api: /student
 *      method: post
 *      status code: 201 CREATED
 *                   4xx bad request
 *                   500 internal server error
 *      request body:
 *          {
 *              "name": "xx"
 *          }
 *      response body:
 *          {
 *               "code": xx
 *               "timestamp": xx
 *               "content": {
 *                   "id" : xx  / error messages
 *               }
 *          }
 *  2. get all students
 *      api: /student?...
 *      method: get
 *      status code: 200 OK
 *                  ..error code
 *      response body
 *          {
 *              ..
 *              "content": [
 *                  {
 *
 *                  },
 *                  {
 *
 *                  }
 *              ]
 *          }
 *  3. get student by id
 *      api: /student/{id}
 *      method: get
 *      ..
 *
 *
 *   homework:
 *      1. create api for student + teacher
 */
