package ru.otus;

import ru.otus.newAnnotation.After;
import ru.otus.newAnnotation.Before;
import ru.otus.newAnnotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class RunTests {
    private Method beforeMethod;
    private Method afterMethod;
    private ArrayList<Method> otherMethods = new ArrayList<>();
    private Class<?> clazz;
    private int countTrueTest;
    private int countAllTest;

    public RunTests(String className) {
        try {
            this.clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public RunTests(Class<?> clazz) {
        this.clazz = clazz;
    }


    private void testClassAnalysis() {
        Method[] methods = clazz.getMethods();

        for (Method value : methods) {
            if (value.isAnnotationPresent(Test.class)) {
                otherMethods.add(value);
            } else if (value.isAnnotationPresent(Before.class)) {
                beforeMethod = value;
            } else if (value.isAnnotationPresent(After.class)) {
                afterMethod = value;
            }
        }

        countAllTest = otherMethods.size();

    }


    private void runTests() {
        for (Method method : otherMethods) {
            try {
                if (beforeMethod != null) {
                    beforeMethod.invoke(instantiate());
                }
                try {
                    method.invoke(instantiate());
                    countTrueTest += 1;
                } catch (Exception e) {
                    System.out.println(method.getName() + " : " + e.toString());

                }

                if (afterMethod != null) {
                    afterMethod.invoke(instantiate());
                }


            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private Object instantiate(Object... args) {
        try {
            if (args.length == 0) {
                return clazz.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return clazz.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public void runningTests() {
        testClassAnalysis();
        runTests();
        System.out.println(String.format("Количество тестов %d из них пройдено %d,упало %d"
                    , countAllTest, countTrueTest, countAllTest - countTrueTest));
    }

}
