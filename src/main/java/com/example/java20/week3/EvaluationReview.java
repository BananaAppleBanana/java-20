package com.example.java20.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * Day1:
 * 1.OOP Concept:
 *         Polymorphism:
 *             runtime: method overriding
 *             compile time: method overloading
 *             upcasting :loose coupling
 *         Inheritance:
 *             all extend Object class
 *         Encapsulation:
 *         Abstraction:
 *             Interface
 *                 variable has to be public final static(multiple interface has same variable)
 *                 java 8: provide static & default method
 *                 Java 9: onwards interfaces allow private and private static methods.
 *             Abstract class
 * 	2.How to override equals() and hashCode(), and why need to
 * 	3. what is static
 * 	    non-static -> this -> new
 * 4. Java is passing by value or reference?
 *      public void fun1() {
 *          List<Integer> list1 = new ArrayList<>();
 *
 *      }
 *
 *      public void fun2(List<Integer> list1) {
 *          list1 = new ArrayList<>()
 *      }
 */
class PassByValueOrReference {
    /**
     *  new ArrayList<>() object xxxfff
     *  list1 00FFXX  [xxxfff]
     */
    public void func1() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(5);
        func2(list1);
        System.out.println(list1);
    }
    //list1 00AAXX [xxxfff]
    //list1 00FFXX  [xxxfff]
    public void func2(List<Integer> list1) {
//        list1 = new ArrayList<>();
        list1.remove(0);
    }

    public static void main(String[] args) {
        new PassByValueOrReference().func1();
    }
}
/**
 * 5. what is final
 * 6. what is immutable
 *
 * Day2:
 * Exceptions checked vs uncheck vs error
 *              Throwable
 *              /       \
 *           Error      Exception
 *                       \
 *                       RuntimeException
 *
 *  how to create customized compile time exception ?
 *   extends Exception
 *  how to create customized run time exception ?
 *   extends RuntimeException
 *
 *  how to get null pointer exception ?
 */
class ThrowNullPointerException {
    public static void main(String[] args) {
//        int[] arr = new int[1];
//        System.out.println(arr[0]);
//        List<Integer> list = null;
//        System.out.println(list.get(0));
//        func1(null);
        throw new NullPointerException("xxx");
    }

    public static void func1(String str) {
        if(str == null) {
            throw new IllegalArgumentException("..");
        }
        for(int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
        }
    }
}

/**
 * How to resolve exceptions
 * Try/catch/finally
 */
class TryCatchFinallyException {
    public static void main(String[] args) {
        int[] arr = new int[Integer.MAX_VALUE];//0 ~ Integer.MAX_VALUE
        func1();
    }

    private static void func1() {
        try {
            func2();
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            System.out.println("this is exception");
        } catch (RuntimeException ex2) {

        } finally {
            System.out.println("this is finally");
        }
    }

    private static void func2() {
        throw new NullPointerException();
    }
}
/**
 * ArrayList vs LinkedList
 *      ArrayList => get(index) => O(1)
 *                => add(index, object) => O(N)
 *      LinkedList => get(index) => O(N)
 *                 => add(index, object) => head / tail => O(1)
 *                                       => middle positions => O(N) time to find + O(1) to insert
 * HashMap workflow
 *      get(Object)
 *      put(Object, Object)
 *      why average O(1) ?
 *            1. hashcode -> hashing value + length of array -> index O(1)
 *            2. LinkedList / Red black tree ->
 *      why does hashcode function return int
 *
 * TreeMap
 *      get
 *      put
 *      LogN
 * PriorityQueue(heap)
 *      offer()
 *      poll()
 *      LogN
 *
 *    map.put(1, 1);
 *    map.put(2, 10);
 *    PriorityQueue<Map.Entry<Integer, Integer>> heap = new ..
 *    for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
 *        heap.offer(entry);
 *    }
 *    map.put(1, 20);
 *
 * Diff between List and Set
 */

/**
 * Day3:
 * Java memory model : what is heap and stack and their difference
 * GC work flow:
 * new Object() -> eden (full / after few gc) -> promote s1 / s0 -> promote old(when hit certain age or previous gens are full)
 * How to create a new Thread, thread life cycle
 * What is volatile? Features that volatile provides. Is volatile itself secure thread safe or not?
 *  fence / barrier + happen rules
 *          write read1 read2 read3
 *          -----> timeline
 *         read1 write  read2 read3
 *          -----> timeline
 *
 *  volatile int i = 0;
 *  i++;
 *      read -> update ->   write
 *                     read
 *
 *  Example of volatile / synchronized
 */
class VolatileExample {
    private volatile static boolean flag;
    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(() -> {
            while(!flag) {}
            System.out.println("break the while loop : flag = " + flag);
        });
        Thread t2 = new Thread(() -> {
            flag = true;
            System.out.println("change flag to true");
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();

        //main thread
        t1.join();
        t2.join();
    }
}


class SynchronizedBlock {
    //class object => waiting list
    public synchronized static void func1() {}

    //this instance => s1 = new SynchronizedBlock();
    public synchronized void func2() {}


    public void func3() {
        //this instance => s2 = new SynchronizedBlock();
        synchronized (this) {}
    }


    public void func4() {
        //class object => waiting list
        synchronized (SynchronizedBlock.class) {

        }
    }

    public static void main(String[] args) {
        //wait() + notify()
    }
}
/**
 *
 * What is synchronized, and what is monitor
 * Pro and cons for synchronized
 * What is Race Condition
 *
 * Day4:
 * What is CAS, is it an atomic operation?
 * What Thread safe collections do you know
        ConcurrentHashMap {{1:10}, {2: 5}}
            T1: put(1,5) finish                                 put(2,10) finish
            --------------------------------------------------------------->timeline
            T2:                   get(1),  get(2)
                                    5       5

        Collections.synchronized(hashmap)
                get(1) => 5
                get(2) => 10

        Hashtable
        ArrayBlockingQueue
        vector
        CopyOnWriteList
        ..

 * How to achieve thread safe?
 * Synchronized
 * Volatile+CAS(why so?)
 * What is ReentrantLock? What does it provide
 * Drawbacks of synchronized
 * What is deadlock
 * How to solve deadlock
 *
 * ***********************************************************************
 * What is threadPool? Why we need it
 * Executor vs ExecutorService vs Executors
 * Difference between callable and runnable
 * What is forkJoinPool, how it works?
 * What type of threadpool you know
 *
 * Day 5
 * What is functional interface
 * What functional interface you know
 * What is lambda expression
 * How we use it
 * What is stream API
 * Can you write a stream api to filter a list of student have age>30
 * Use stream api to counting frequency
 * What is Optional
 * How does parallel stream worked
 * What is CompletableFuture, what advantage does it provide us
 *
 * Day 6
 * What is database, different type of database
 * What database do you know
 *
 * Write sql queries
 * Select, from, where, order by, aggregation function
 * Rank dense_rank
 * Union, intersect, minus,
 * Inner join, left join, right join, full join, cross join
 * Group by, having
 *
 * Day 7
 * What is transaction
 * What is ACID
 * Four isolation levels
 * Each isolation level has what problem? (dirty read, unrepeatable read, phantom read)
 * What is share lock, exclusive lock
 * What is optimistic lock
 *
 * Day 8
 * How does rdbms do index (B tree, B+tree)
 * Why we need to create index
 * Execution plan (tuning database)
 * Table design :1-1, 1-m, m-m
 * 1st, 2nd, 3rd normalization
 *
 *
 *
 *
 * ***********
 * Java section
 * Java design patterns
 * Reflection
 *
 * Framework section
 * JDBC + ORM(hibernate + jpa)
 * Spring IOC AOP + Spring Boot
 * network + server
 * Restful API
 * Security
 *
 * Microservice section
 * introduction
 * spring cloud
 * CAP + single leader cluster / multi leader cluster / leaderless cluster
 * cassandra mongodb cluster
 * global transaction(2pc, 3pc, saga)
 * message queue
 *
 * Deployment + daily work
 * git branches + Agile scrum
 * jenkins pipeline
 * AWS services
 * AWS ECS + docker
 */