package ru.otus;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String uniqueGroupTag = "Professors at OTUS";
        List<String> namesList = ImmutableList.of("Sergei", "Vadim", "Paul", "Alexander", "Vyacheslav", "Vitaliy");

        tableCreation(namesList, uniqueGroupTag);

    }

    public static void tableCreation(Collection<String> namesCollection, String uniqueGroupTag) {

        print(uniqueGroupTag + ": ");
        print(Strings.padEnd("", 20, '_'));

        namesCollection.stream()
                .map(name -> Strings.padStart(name, 15, '.'))
                .forEach(App::print);


    }

    public static void print(String s) {
        System.out.println(s);
    }
}
