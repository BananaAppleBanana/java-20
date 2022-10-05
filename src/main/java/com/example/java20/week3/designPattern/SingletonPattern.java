package com.example.java20.week3.designPattern;

import javax.management.InstanceAlreadyExistsException;
import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 *
 *  lazy loading
 */
class LazyLoadingNotThreadSafe {
    private static LazyLoadingNotThreadSafe instance;

    private LazyLoadingNotThreadSafe() {
        if(instance != null) {
            throw new RuntimeException();
        }
    }

    public static LazyLoadingNotThreadSafe getInstance() {
        if(instance == null) {
            instance = new LazyLoadingNotThreadSafe();
        }
        return instance;
    }

    public void print() {
        System.out.println("this is print");
    }
}


class LazyLoadingDoubleCheck {
    private static volatile LazyLoadingDoubleCheck instance;

    private LazyLoadingDoubleCheck() {
    }
    public static LazyLoadingDoubleCheck getInstance() {
        if(instance == null) {
            synchronized (LazyLoadingDoubleCheck.class) {
                if(instance == null) {
                    instance = new LazyLoadingDoubleCheck();
                }
            }
        }
        return instance;
    }
}


class SingletonTest {
    public static void main(String[] args) throws Exception{
        LazyLoadingNotThreadSafe ins1 = LazyLoadingNotThreadSafe.getInstance();
        Class clazz = LazyLoadingNotThreadSafe.class;
        Constructor constructor = clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        LazyLoadingNotThreadSafe ins2 = (LazyLoadingNotThreadSafe) constructor.newInstance();
        System.out.println(ins1 == ins2);
    }
}

/**
 *  eager loading
 *
 *
 *  when will java load class object?
 *      1. class loader
 *      2. first time new XX()
 *      3. Class.forName()
 *      4. first time Class.static
 *      ..
 */

class EagerLoading {
    private static final EagerLoading instance = new EagerLoading();

    private EagerLoading() {}

    public static EagerLoading getInstance() {
        return instance;
    }
}

/**
 *  enum
 */
enum EnumSingleton {
    INSTANCE1, INSTANCE2;
}

/**
 * problem
 *  1. deep copy / serializable
 *  2. cloneable
 *  3. reflection
 */

