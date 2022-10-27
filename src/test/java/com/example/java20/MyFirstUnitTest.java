package com.example.java20;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class MyFirstUnitTest {

    private A a;

    @BeforeEach
    public void init() {
        B b = mock(B.class);
        when(b.get()).thenReturn(20);
        a = new A(b);
    }

    @Test
    public void testInput() {
        assertTrue(a.get() == 40);
    }

    @Test
    public void testException() {
        assertThrows(RuntimeException.class, () -> a.get());
    }
}

class A {
    private final B b;

    public A(B b) {
        this.b = b;
    }

    public int get() {
        int x = b.get();
        x *= 2;
        if(x <= 20) {
            throw new RuntimeException("xx");
        }
        return x;
    }
}

class B {
    public int get() {
        return 5;
    }
}