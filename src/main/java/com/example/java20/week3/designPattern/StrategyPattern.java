package com.example.java20.week3.designPattern;

public class StrategyPattern {

    public static void main(String[] args) {
        CalculateStrategy s1 = (v1, v2) -> v1 + v2;
        CalculateStrategy s2 = (v1, v2) -> v1 * v2;
        System.out.println(calculate(s1, 1, 2));
        System.out.println(calculate(s2, 1, 2));
    }

    public static int calculate(CalculateStrategy strategy, int v1, int v2) {
        return strategy.execute(v1, v2);
    }
}

interface CalculateStrategy {
    int execute(int v1, int v2);
}
