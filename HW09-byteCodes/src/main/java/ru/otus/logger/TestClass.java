package ru.otus.logger;

import ru.otus.newAnnotation.Log;

public class TestClass {
    @Log
    public void testMethod1(Integer param, String param2, int param3) {
        System.out.println(param + param2 + param3);
    }

    @Log
    public void testMethod2(String param, int param2) {
        System.out.println(param + param2);
    }

    @Log
    public void testMethod3(int param, Integer param2) {
        System.out.println(param + param2);
    }

    @Log
    public void testMethod4() {
        System.out.println("SPECIAL");
    }

}
