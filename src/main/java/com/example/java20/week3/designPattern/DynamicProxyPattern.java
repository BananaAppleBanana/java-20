package com.example.java20.week3.designPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxyPattern {

    public static void main(String[] args) {
        Square proxy = (Square) Proxy.newProxyInstance(
                DynamicProxyPattern.class.getClassLoader(),
                new Class[]{Square.class},
                new SquareInvocationHandler(new SquareImpl())
        );
//        proxy.print();

        List<Integer> proxyList = (List) Proxy.newProxyInstance(
                DynamicProxyPattern.class.getClassLoader(),
                new Class[]{List.class},
                new ListInvocationHandler(new ArrayList<>())
        );
        proxyList.add(1);
        proxyList.add(2);
        System.out.println(proxyList.isEmpty());
        System.out.println(proxyList.size());

    }
}
class ListInvocationHandler implements InvocationHandler {
    private final List<Integer> list;

    public ListInvocationHandler(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        System.out.println(method);
        Object obj = method.invoke(list, args);
        System.out.println("after");
        return obj;
    }
}

class SquareInvocationHandler implements InvocationHandler {
    private final Square squareImpl;

    public SquareInvocationHandler(Square squareImpl) {
        this.squareImpl = squareImpl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        System.out.println(method);
        Object obj = method.invoke(squareImpl, args);
        System.out.println("after");
        return obj;
    }
}



