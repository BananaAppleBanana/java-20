package com.example.java20.week3.designPattern;

public class AdaptorPattern {
    public static void main(String[] args) {
        Circle circle = () -> System.out.println("this is circle");
        Square square = new SquareAdaptor(circle);
        func1(square);
    }

    public static void func1(Square square) {
        square.print();
    }
}

interface Square {
    void print();
}

interface Circle {
    void print();
}

class SquareAdaptor implements Square {
    private final Circle circle;

    public SquareAdaptor(Circle circle) {
        this.circle = circle;
    }

    @Override
    public void print() {
        //..
        circle.print();
        //..
    }
}



