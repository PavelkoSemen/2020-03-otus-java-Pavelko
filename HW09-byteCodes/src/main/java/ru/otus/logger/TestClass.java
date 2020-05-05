package ru.otus.logger;

import ru.otus.newAnnotation.Log;

public class TestClass {
    @Log
    public void testMethod1(int param){
        System.out.println(param);
    }

    @Log
    public void testMethod2(int param){
        System.out.println(param);
    }
    @Log
    public void testMethod3(int param){
        System.out.println(param);
    }
}
