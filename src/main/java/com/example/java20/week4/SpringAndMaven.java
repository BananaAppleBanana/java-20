package com.example.java20.week4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

/**
 *  Spring : IOC, AOP
 *  IOC(concept):
 *      Dependency injection(implementation)
 *      1. Application context as IOC container(Factory pattern, Map)
 *      2. @Component
 *      3. @Autowired
 *          a. field injection (do not use field injection)
 *          b. constructor injection
 *          c. setter injection
 *      4. bean scope
 *          a. singleton (default)
 *          b. prototype
 *          c. request
 *          d. session
 *          e. global session
 *      5. by type vs by name
 *  AOP
 *
 *      class AspectXX {
 *          @After
 *          location
 *          public void printAfter() {
 *              //after
 *          }
 *
 *          @Before
 *          location
 *          public void printBefore() {
 *              //before
 *              log
 *          }
 *
 *          @PointCut(execute(all public functions / classes under xx package))
 *          public .. location() {
 *              ..
 *          }
 *      }
 *
 *  SpringBoot
 *      1. main starter / entry pointer
 *      2. auto configuration / enable auto scan
 *      3. application.properties / yml
 *      4. jar -> java command to execute jar
 *      5. embedded tomcat
 *      6. actuator => embedded endpoints ..'
 *
 *
 *   write aop @before + @after + @around your Hibernate CRUD methods => print log info
 *
 *
 *   Today 3PM CDT
 */
@SpringBootApplication
class MySpringBoot1 {


    private static StudentService studentService1;
    private static StudentService studentService2;

    @Autowired
    public MySpringBoot1(@Qualifier("abc") StudentService studentService1
            , @Qualifier("abc") StudentService studentService2) {
        MySpringBoot1.studentService1 = studentService1;
        MySpringBoot1.studentService2 = studentService2;
    }


    public MySpringBoot1(@Qualifier("abc") StudentService studentService1) {
        MySpringBoot1.studentService1 = studentService1;
    }

    public static void main(String[] args) {
        SpringApplication.run(MySpringBoot1.class, args);
        System.out.println(studentService1 == studentService2);
    }
}

@Component
interface StudentService {}
@Component("abc") //bean
class StudentServiceImpl implements StudentService {
    @Override
    public String toString() {
        return "StudentServiceImpl{}";
    }
}