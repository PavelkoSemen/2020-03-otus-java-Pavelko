package ru.otus;


import ru.otus.logger.TestClass;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        new TestClass().testMethod1(26,"sd",23);
//        new TestClass().testMethod2("sd",26);
//        new TestClass().testMethod3(26,23);
//        new TestClass().testMethod4();

        List<Integer> list = Arrays.asList(1,2,3,4);
        list.add(0,5);
        System.out.println(list);
    }
}