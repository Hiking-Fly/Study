package cn.zjnu.io;


import java.io.*;

public class ChangeIO {
    public static void main(String[] args) throws IOException {
        show1();
    }

    private static void show1() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("a.txt"),"gbk");
        char buf[] = new char[1024];
        inputStreamReader.read(buf);
        System.out.println(new String(buf));
        inputStreamReader.close();

    }

    private static void show() throws IOException {
        //装换流，InputStreamReader,可以指定编码方式
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("a.txt"));
        outputStreamWriter.write("你好");
        outputStreamWriter.close();
    }
}
