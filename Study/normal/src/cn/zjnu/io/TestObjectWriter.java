package cn.zjnu.io;

import java.io.*;

public class TestObjectWriter {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        show();
    }

    private static void show() throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream("a.txt"));
        Person person = new Person(12,"尊");
        objectOutputStream.writeObject(person);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("a.txt"));
        Person person1 = (Person)objectInputStream.readObject();
        System.out.println(person1);

    }
}
