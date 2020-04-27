package ru.otus.test;

import ru.otus.newAnnotation.After;
import ru.otus.newAnnotation.Before;
import ru.otus.newAnnotation.Test;
import sun.security.pkcs.ParsingException;


public class ExampleTest {
    public ExampleTest() {
    }

    @Before
    public void Before1() {
        System.out.println("This is Before1");
    }

    @Before
    public void Before2() {
        System.out.println("This is Before2");
    }

    @Before
    public void Before3() {
        System.out.println("This is Before3");
    }

    @Test
    public void method1() {
        System.out.println("This is method1");

    }

    @Test
    public void method2() {
        System.out.println("This is method2");

    }

    @Test
    public void method3() {
        System.out.println("This is method3");
        throw new NullPointerException();
    }

    @Test
    public void method4() {
        System.out.println("This is method4");

    }

    @Test
    public void method5() throws ParsingException {
        System.out.println("This is method5");
        throw new ParsingException();
    }

    @Test
    public void method6() {
        System.out.println("This is method6");

    }

    @Test
    public void method7() {
        System.out.println("This is method7");

    }

    @After
    public void After1() {
        System.out.println("This is After1");

    }

    @After
    public void After2() {
        System.out.println("This is After2");

    }

    @After
    public void After3() {
        System.out.println("This is After3");

    }
}
