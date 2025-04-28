package com.lab.serializationtest;

import java.io.*;

public class Person implements Serializable {
    private static final long serialVersionUID = 2L;

    private int height;
    private int age;
    private String name;
    private int footSize;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFootSize() {
        return footSize;
    }

    public void setFootSize(int footSize) {
        this.footSize = footSize;
    }

    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(getAge());
        oos.writeObject(getHeight());
        oos.writeObject(getName());
        oos.writeObject(getFootSize());
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        Integer age = (Integer) ois.readObject();
        Integer height = (Integer) ois.readObject();
        String name = (String) ois.readObject();
        Integer footSize = (Integer) ois.readObject();
        this.setHeight(height);
        this.setAge(age);
        this.setName(name);
        this.setFootSize(footSize);
    }
}
