package ru.otus;

import ru.otus.newAnnotation.After;
import ru.otus.newAnnotation.Before;
import ru.otus.newAnnotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRunner {
    private List<Method> beforeMethods = new ArrayList<>();
    private List<Method> afterMethods = new ArrayList<>();
    private List<Method> otherMethods = new ArrayList<>();
    private Class<?> clazz;
    private int countTrueTest;
    private int countAllTest;

    public TestRunner(String className) {
        try {
            this.clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public TestRunner(Class<?> clazz) {
        this.clazz = clazz;
    }


    private void analyzeTestClass() {

        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                otherMethods.add(method);
            } else if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }

        countAllTest = otherMethods.size();

    }


    private void runTests() {
        for (Method method : otherMethods) {
            Object testObject = instantiate();

            beforeMethods.forEach(beforeMethod -> {
                try {
                    beforeMethod.invoke(testObject);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });

            try {
                method.invoke(testObject);
                countTrueTest += 1;
            } catch (Exception e) {
                System.out.println(method.getName() + " : " + e.toString());
            }

            afterMethods.forEach(afterMethod -> {
                try {
                    afterMethod.invoke(testObject);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    private Object instantiate() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void runningTests() {
        analyzeTestClass();
        runTests();
        System.out.println(String.format("Количество тестов %d из них пройдено %d,упало %d"
                , countAllTest, countTrueTest, countAllTest - countTrueTest));
    }

}
