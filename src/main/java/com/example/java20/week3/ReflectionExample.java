package com.example.java20.week3;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ReflectionExample {
    private static class ReflectionStudent {
        String name;

        public ReflectionStudent(String name) {
            this.name = name;
        }

        @Override
        @MyAnnotation(value = "abc")
        public String toString() {
            return "ReflectionStudent{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception {
        ReflectionStudent stu = new ReflectionStudent("Tom");
        Class<?> stuClass = ReflectionStudent.class;
//        Field[] fields = stuClass.getDeclaredFields();
//        for(Field f: fields) {
//            System.out.println(f);
//            f.setAccessible(true);
//            f.set(stu, "Jerry");
//        }
        for(Method method: stuClass.getDeclaredMethods()) {
            if(method.getDeclaredAnnotations()[0].annotationType() == MyAnnotation.class) {
                MyAnnotation annotation = (MyAnnotation) method.getDeclaredAnnotations()[0];
                System.out.println(annotation.value());
            }
        }
//        System.out.println(stu);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation {
    String value() default "str";
}

/**
 *  1. install rdbms in your laptop
 *      mac -> postgre  / mysql / oracle(docker)
 *      windows -> oracle / postgre / mysql
 *      RDS / Aurora  -> databases
 *  2. coding challenge
 *      a. create customized annotation @Component , @Inject
 *      b. @Component
 *         class A {}
 *         your application should load dynamic proxy instance of A into concurrentHashMap
 *      c.  @Component
 *          class B {
 *              @Inject
 *              private A a
 *         }
 *         inject the proxy instance A into field a from concurrent hashmap
 *
 *  deadline : tomorrow 10am CDT
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Component {}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject{}

interface A {
    void print();
}
@Component
class AImpl implements A {
    @Override
    public void print() {
        System.out.println("this is A");
    }
}
@Component
class B {
    @Inject
    private A a;

    public void print() {
        a.print();
    }
}

class Container {
    private final Map<Class<?>, Object> map = new ConcurrentHashMap<>();

    public List<Class<?>> scan() {
        return Arrays.asList(AImpl.class, B.class);
    }

    public void doInject() throws Exception{
//        List<Class<?>> classes = scan();
//        for(Class<?> clazz: classes) {
//            Annotation[] annotations = clazz.getDeclaredAnnotations();
//            for(Annotation annotation: annotations) {
//                if(annotation.annotationType() == Component.class) {
//                    Constructor constructor = clazz.getDeclaredConstructor();
//                    Object instance = constructor.newInstance();
//                    map.put(clazz, instance);
//                }
//            }
//        }
        Class<?> aClass = AImpl.class;
        A instanceA = (A)aClass.getDeclaredConstructor().newInstance();
        A a = (A) Proxy.newProxyInstance(
                Container.class.getClassLoader(),
                aClass.getInterfaces(),
                new AInvocationHandler(instanceA)
        );
        map.put(aClass.getInterfaces()[0], a);

        Class<?> bClass = B.class;
        B instanceB = (B)bClass.getDeclaredConstructor().newInstance();
        for(Field field: bClass.getDeclaredFields()) {
            field.setAccessible(true);
            if(map.containsKey(field.getType())) {
                Object obj = map.get(field.getType());
                field.set(instanceB, obj);
            }
        }
        map.put(bClass, instanceB);
    }

    public void print() {
        ((B) map.get(B.class)).print();
    }

    public static void main(String[] args) throws Exception{
        Container c = new Container();
        c.doInject();
        c.print();
    }
}

class AInvocationHandler implements InvocationHandler {
    private final A a;

    public AInvocationHandler(A a) {
        this.a = a;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object res = method.invoke(a, args);
        System.out.println("after");
        return res;
    }
}


//class ComputeTest {
//    public static void main(String[] args) {
//        Map<Integer, Integer> m = new ConcurrentHashMap<>();
//        m.compute(5, (k, v) -> v == null ? 1: v + 1);
//        System.out.println(m);
//    }
//}

