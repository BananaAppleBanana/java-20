package com.example.java20.week3;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflectionExample {
    private static class ReflectionStudent {
        String name;

        public ReflectionStudent(String name) {
            this.name = name;
        }

        @Override
        @MyAnnotation(value = "abc")
        public String toString() {
            return "ReflectionStudent{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception {
        ReflectionStudent stu = new ReflectionStudent("Tom");
        Class<?> stuClass = ReflectionStudent.class;
//        Field[] fields = stuClass.getDeclaredFields();
//        for(Field f: fields) {
//            System.out.println(f);
//            f.setAccessible(true);
//            f.set(stu, "Jerry");
//        }
        for(Method method: stuClass.getDeclaredMethods()) {
            if(method.getDeclaredAnnotations()[0].annotationType() == MyAnnotation.class) {
                MyAnnotation annotation = (MyAnnotation) method.getDeclaredAnnotations()[0];
                System.out.println(annotation.value());
            }
        }
//        System.out.println(stu);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation {
    String value() default "str";
}

/**
 *  1. install rdbms in your laptop
 *      mac -> postgre  / mysql / oracle(docker)
 *      windows -> oracle / postgre / mysql
 *      RDS / Aurora  -> databases
 *  2. coding challenge
 *      a. create customized annotation @Component , @Inject
 *      b. @Component
 *         class A {}
 *         your application should load dynamic proxy instance of A into concurrentHashMap
 *      c.  @Component
 *          class B {
 *              @Inject
 *              private A a
 *         }
 *         inject the proxy instance A into field a from concurrent hashmap
 *
 *  deadline : tomorrow 10am CDT
 */