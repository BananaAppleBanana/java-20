package com.example.java20.week3;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
 * generic
 */
class GenericTest {
    public static void main(String[] args) {
        Set set = new HashSet<>();
        set.add("aa");
        String res = get("abc");
    }

    public static <E> E get(E e) {
        return e;
    }
}
/**
 * comparator vs comparable
 */
class CompareExample {
    private static class Student implements Comparable{
        int id;

        public Student(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            return id - ((Student)o).id;
        }
    }

    public static void main(String[] args) {
//        Map<Student, Integer> m = new TreeMap<>((s1, s2) -> s1.id - s2.id);
        Map<Student, Integer> m = new TreeMap<>();
        m.put(new Student(1), 1);
        m.put(new Student(2), 10);
        System.out.println(m);
    }
}
/**
 * What is threadPool? Why we need it
 * Executor vs ExecutorService vs Executors
 * Difference between callable and runnable
 * What is forkJoinPool, how it works?
 * What type of threadpool you know
 * What is CompletableFuture, what advantage does it provide us
 */
class CFExample {
    private static final ExecutorService pool = Executors.newCachedThreadPool();;

    public static void main(String[] args) {
        int res = executeTask();
        System.out.println(res);
    }

    public static int executeTask() {
        List<CompletableFuture<Integer>> list = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            final int tmp = i;
            list.add(CompletableFuture.supplyAsync(() -> tmp, pool)
                    .thenApplyAsync(x -> {
                        try {
                            System.out.println(Thread.currentThread());
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return x * 2;
                    }));
        }
        CompletableFuture[] cfArr = list.toArray(new CompletableFuture[0]);
        return CompletableFuture.allOf(cfArr)
                .thenApply(Void -> list.stream().map(cf -> cf.join()).reduce(0, (x, y) -> x + y))
                .orTimeout(6, TimeUnit.SECONDS)
                .join();
    }
}

/**
 * Day 5
 * What is functional interface
 */
class ParentA {
    public void get() {}
}
class ParentB extends ParentA {
    @Override
    public void get() {}
}

/**
 * What functional interface you know
 * What is lambda expression
 */
class LambdaExpression {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>((v1, v2) -> v1 - v2);
    }
}
/**
 * How we use it
 * What is stream API / Why stream API
 */
class StreamAPIExample {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 2, 2, 2)
                .stream()
                .map(x -> x + 3)        //reference pipeline 1
                .distinct()
                .sorted()
//                .map(s -> String.valueOf(s)) //reference pipeline 2
//                .filter(s -> s.startsWith("5")) //reference pipeline 3
                .forEach(System.out::println); //terminal operation => generate iterator -> sink1 -> sink2 -> sink3 -> final sink
    }
}
/**
 * Can you write a stream api to filter a list of student have age>30
 * Use stream api to counting frequency
 * What is Optional
 * How does parallel stream worked
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Day 6
 * What is database, different type of database
 * What database do you know
 *
 * Write sql queries
 * Select, from, where, order by, aggregation function
 *
 * Rank dense_rank
 * Union, intersect, minus,
 * Inner join, left join, right join, full join, cross join
 *      join  ... on ..
 *        ,
 * Group by, having
 *
 * select *
 * from A join B on A.id = B.a_id
 *
 * select *
 * from A, B
 * where A.id = B.a_id
 * * * * * * * * * * * * * * * *
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
 *      merge join => merge two sorted array
 *      nested loop join =>
 *          for(...) {
 *              for(..) {
 *
 *              }
 *          }
 *     hash join => [row1, row5, row10][][][][][row2, row3][][] buckets
 * Table design :1-1, 1-m, m-m
 * 1st, 2nd, 3rd normalization
 *
 *                                          client
 *                                             |
 *    disk                  [Private sql area1] connection1 : session / query status / merge, sort, group by ...
 *                          [Private sql area2] connection2
 *                          [Private sql area3] connection3
 *                                            |
 *    disk                  [global shared area]
 *                                  first part
 *                                  1. data dictionary : table info / column info / privilege info
 *                                  2. ..  : execution plan
 *                                  3. result cache area :
 *
 *                                  2nd parts
 *    disk                          1. shared area : blocks : index + cache part of table / rows
 *                                  2. undo area : transaction
 *                                  3. redo area : insert, update , delete ...
 *
 *
 *
 *  Bitmap index
 *
 *  homework
 *  1. push code to github -> private -> banana apple..
 *  2. write the read me
 *  3. deadline is tomorrow 10amCDT
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