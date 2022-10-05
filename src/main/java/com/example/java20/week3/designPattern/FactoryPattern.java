package com.example.java20.week3.designPattern;

/**
 *  1. hide initialization
 *  2. loose coupling
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Car Abstract class
 *   |
 *  BMW Factory / Merc Factory
 *  -------------------------
 *      factory method
 */

class Factories {
    private Factories() {}

    public static Object getInstance() {
        return new Object();
    }
}
/**
 * Tomorrow
 *  builder
 *  composition - aggregation
 *  bridge
 *  strategy
 *  observer
 *  adaptor
 *  static proxy / decorator
 *  dynamic proxy
 *
 *  SOLID principle
 *
 *  reflection
 *
 *
 *  This afternoon 2pm cdt =>
 */