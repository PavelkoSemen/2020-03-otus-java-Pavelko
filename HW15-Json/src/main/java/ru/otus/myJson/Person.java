package ru.otus.myJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int age;
    private List<String> stringList;
    public ArrayList<Integer> integerArrayList;
    public int [] array = new int[]{1, 2, 3, 4};

    public Person(String name, int age, ArrayList<String> stringArrayList, ArrayList<Integer> integerArrayList) {
        this.name = name;
        this.age = age;
        this.stringList = stringArrayList;
        this.integerArrayList = integerArrayList;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person that = (Person) obj;
        return name == that.name &&
                age == that.age;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }


}
