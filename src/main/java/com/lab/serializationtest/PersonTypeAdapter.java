package com.lab.serializationtest;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class PersonTypeAdapter extends TypeAdapter<Person> {
    public Person read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        String data = reader.nextString();
        String[] parts = data.split(";");
        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        int height = Integer.parseInt(parts[2]);
        int footSize = Integer.parseInt(parts[3]);
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setHeight(height);
        person.setFootSize(footSize);
        return person;
    }

    public void write(JsonWriter writer, Person value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        String data = value.getName() + ";" + value.getAge() + ";" + value.getHeight() + ";" + value.getFootSize();
        writer.value(data);
    }
}