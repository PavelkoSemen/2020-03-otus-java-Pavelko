package ru.otus;


import ru.otus.logger.TestClass;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        new TestClass().testMethod1(26,"sd",23);
        new TestClass().testMethod2("sd",26);
        new TestClass().testMethod3(26,23);
        new TestClass().testMethod4();

    }
}