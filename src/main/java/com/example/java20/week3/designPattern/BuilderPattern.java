package com.example.java20.week3.designPattern;



public class BuilderPattern {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        System.out.println(s1 == s2); //true / false
        System.out.println(s2 == s3); //true / false

        Integer v1 = 100;
        Integer v2 = 100;
        Integer v3 = 128;
        Integer v4 = 128;
        System.out.println(v1 == v2);
        System.out.println(v3 == v4);

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < 100; i++)
            result.append(String.valueOf(i));

    }
}


/**
 *
 */

class Day1Student {
    private String name;
    private int age;
    //..
    //..

    public Day1Student() {
    }

    public Day1Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Day1Student setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Day1Student setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Day1StudentBuilder {
    private String name;
    private int age;

    public Day1StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Day1StudentBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public Day1Student build() {
        return new Day1Student(name, age);
    }
}
class BuilderExample {
    public static void main(String[] args) {
        Day1Student stu1 = new Day1Student().setAge(5).setName("toM");
        Day1Student stu2 = new Day1Student("toM", 5);
        Day1Student stu3 = new Day1StudentBuilder().setAge(5).setName("Tom").build();
        System.out.println(stu3);
    }
}