package ru.otus;

import ru.otus.newAnnotation.After;
import ru.otus.newAnnotation.Before;
import ru.otus.newAnnotation.Test;
import sun.security.pkcs.ParsingException;


public class ExampleTest {
    public ExampleTest() {
    }

    @Before
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

    }
    @Test
    public void method4() {
        System.out.println("This is method3");
        throw new NullPointerException();
    }
    @Test
    public void method5() {
        System.out.println("This is method3");

    }
    @Test
    public void method6() throws ParsingException {
        System.out.println("This is method3");
        throw new ParsingException();
    }
    @Test
    public void method7() {
        System.out.println("This is method3");

    }
    @Test
    public void method8() {
        System.out.println("This is method3");

    }

    @After
    public void method9() {
        System.out.println("This is method4");

    }
}
