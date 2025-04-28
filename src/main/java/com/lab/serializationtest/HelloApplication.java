package com.lab.serializationtest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class HelloApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String jsonFilePath = "test.json";
        String binaryFilePath = "test_bin.dat";

        Person person = createPerson("Joe", 20);
        Person person2 = createPerson("Mike", 30);

        List<Person> persons = new LinkedList<>();
        persons.add(person);
        persons.add(person2);

        try (FileOutputStream fileOutputStream
                     = new FileOutputStream(binaryFilePath);
             ObjectOutputStream objectOutputStream
                     = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(persons);
            objectOutputStream.flush();
        }

        try (Writer writer = new FileWriter(jsonFilePath)) {
            GsonBuilder builder = new GsonBuilder();

            builder.registerTypeAdapter(Person.class, new PersonTypeAdapter());
            Gson gson = builder.create();
            gson.toJson(person, writer);
            writer.flush();
        }

        List<Person> p3list;
        try (FileInputStream fileInputStream
                     = new FileInputStream(binaryFilePath);
             ObjectInputStream objectInputStream
                     = new ObjectInputStream(fileInputStream)
        ) {
            p3list = (List<Person>) objectInputStream.readObject();
        }

        System.out.println("p3list: " + p3list.getClass().getName());
        for (var item : p3list) {
            printPerson(item);
        }

        Person p2;
        try (FileReader reader = new FileReader(jsonFilePath)) {
            GsonBuilder builder1 = new GsonBuilder();
            builder1.registerTypeAdapter(Person.class, new PersonTypeAdapter());
            Gson gson1 = builder1.create();

            p2 = gson1.fromJson(reader, Person.class);
        }
        System.out.println("p2");
        printPerson(p2);
    }

    private static Person createPerson(String name, int age) {
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        person.setHeight(160);
        person.setFootSize(40);
        return person;
    }

    private static void printPerson(Person p2) {
        if (p2 == null) {
            System.out.println("person = null");
        } else {
            System.out.println("person = {" + p2.getAge() + ", " + p2.getName() + ", " + p2.getHeight() + ", " + p2.getFootSize() + "}");
        }
    }
}