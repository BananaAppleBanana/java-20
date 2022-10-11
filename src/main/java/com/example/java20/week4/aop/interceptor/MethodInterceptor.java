package com.example.java20.week4.aop.interceptor;


import com.example.java20.week4.aop.MethodInvocation;

public interface MethodInterceptor {
    Object invoke(MethodInvocation mi) throws Throwable;
}
