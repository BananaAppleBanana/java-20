package com.example.java20.week4.aop;

import com.example.java20.week4.aop.interceptor.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;

public class ProxyMethodInvocation implements MethodInvocation{

    private List<MethodInterceptor> interceptors;
    private int idx;
    private Object originObj;
    private Method originMethod;
    private Object[] args;

    public ProxyMethodInvocation(List<MethodInterceptor> interceptors, Object originObj, Method originMethod, Object[] args) {
        this.interceptors = interceptors;
        this.originObj = originObj;
        this.originMethod = originMethod;
        this.args = args;
    }

    @Override
    public Object proceed() throws Throwable{
        if(idx >= interceptors.size()) {
            return executeOriginalMethod();
        }
        MethodInterceptor interceptor = interceptors.get(idx++);
        return interceptor.invoke(this);
    }

    private Object executeOriginalMethod() throws Throwable{
        originMethod.setAccessible(true);
        return originMethod.invoke(originObj, args);
    }
}

/**
 *  mi = new ProxyMethodInvocation();
 *  [After,  Before]
 *                  idx
 *   1. AfterMethodInterceptor
 *         Object result = mi.proceed();
 *                  2. execute BeforeMethodInterceptor
 *                      run before logic / function // print before
 *                      return mi.proceed();
 *                              3. return executeOriginalMethod()
 *     run after function / print after
 *
 */