package ru.otus;

import ru.otus.test.ExampleTest;
import ru.otus.test.TestRunner;

public class Main {
    public static void main(String[] args) {

        new TestRunner(ExampleTest.class).runningTests();

    }
}
