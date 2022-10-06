package com.example.java20.week3.designPattern;

public class StaticProxyPattern {
    public static void main(String[] args) {
        Square square = new SquareImpl();
        Square proxy = new SquareStaticProxy(square);
        proxy.print();
    }
}


class SquareImpl implements Square {
    @Override
    public void print() {
        System.out.println("this is square");
    }
}



class SquareStaticProxy implements Square {
    private final Square square;

    public SquareStaticProxy(Square square) {
        this.square = square;
    }

    @Override
    public void print() {
        System.out.println("before");
        square.print();
        System.out.println("after");
    }
}