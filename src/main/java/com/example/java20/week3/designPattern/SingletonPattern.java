package com.example.java20.week3.designPattern;

import javax.management.InstanceAlreadyExistsException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

class ComparatorTest {
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(3, 5, 1, 4, 2, 1, 1, 1);
//        list.sort((v1, v2) -> {
//            if(v1 > v2) {
//                return 1; //swap v1 , v2
//            } else if(v1 < v2) {
//                return -1; //do nothing
//            } else {
//                return 0; //they are same
//            }
////            return v2 - v1;
//        });
//        System.out.println(list);
//
//        Comparator<Integer> cpt1 = (v1, v2) -> v2 - v1;
//        Comparator<String> cpt2 = (v1, v2) -> v2.compareTo(v1);
        Map<String, Integer> songs = new HashMap<>();
        TreeMap<String, Integer> map = new TreeMap<>((id1, id2) -> songs.get(id2) - songs.get(id1));
        songs.put("id_1", 10);
        map.put("id_1", 10);
        songs.put("id_2", 30);
        map.put("id_2", 30);
        System.out.println(map);
        songs.put("id_2", 5);
        map.put("id_2", 5);
        System.out.println(map);
//        Map<Integer, Integer> m1 = new ConcurrentHashMap<>();
//        m1.compute(5, (k, v) -> k == null ? v : v + 1);

        //why try + finally to release resources?
        /**
         *  1. out of memory error
         */

    }

    public static <K, V extends Comparable<V>> Map<K, V> sort(Map<K, V> map) {
         Map<K, V> res = new TreeMap((k1, k2) -> map.get(k2).compareTo(map.get(k1)));
         res.putAll(map);
         return res;
    }
}