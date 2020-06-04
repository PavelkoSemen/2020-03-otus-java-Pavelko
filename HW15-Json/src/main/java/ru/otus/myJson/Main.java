package ru.otus.myJson;


import com.google.gson.Gson;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Gson gson = new Gson();

        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("Otus1");
        stringArrayList.add("Otus2");
        stringArrayList.add("Otus3");

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(1);
        integerArrayList.add(2);
        integerArrayList.add(3);

        Person person = new Person("sem", 16, stringArrayList, integerArrayList);

        MyJson myJson = new MyJson();

        String toJson = myJson.toJson(person);

        Person person1 = gson.fromJson(toJson, Person.class);


        System.out.println(myJson.toJson(person));

        System.out.println(toJson);
        System.out.println(gson.toJson(person));

        System.out.println(person1.equals(person));



    }

}
