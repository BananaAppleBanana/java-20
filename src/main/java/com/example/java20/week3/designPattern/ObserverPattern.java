package com.example.java20.week3.designPattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
}

class Topic {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber sub) {
        subscribers.add(sub);
    }

    public void publish(String msg) {
        for(Subscriber sub: subscribers) {
            sub.receive(msg);
        }
    }
}

class Subscriber {
    public void receive(String msg) {
        System.out.println(msg);
    }
}