package cn.zjnu.io;

import java.io.*;
import java.util.HashMap;

public class BufferIO {
    public static void main(String[] args) throws IOException {
        show3();
    }

    private static void show3() throws IOException {
        //文本排序练习
        HashMap<Integer,String> Tea = new HashMap<>();
        //bufferedReader
        BufferedReader bufferedReader = new BufferedReader(new FileReader("a.txt"));
        String buf;
        String [] s=null;
        while((buf=bufferedReader.readLine())!=null){
            System.out.println(buf);
            s = buf.split("\\.");
            if(s.length>=2)
                Tea.put(Integer.parseInt(s[0]),s[1]);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("a.txt"));
        for(Integer n:Tea.keySet()){
            StringBuilder line= new StringBuilder();
            line.append(n);
            line.append(".");
            line.append(Tea.get(n));
            bufferedWriter.write(line.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    private static void show2() throws IOException{
        //bufferedReader
        BufferedReader bufferedReader = new BufferedReader(new FileReader("a.txt"));
        String buf;
        while((buf=bufferedReader.readLine())!=null){
            System.out.println(buf);
        }
        bufferedReader.close();

    }

    private static void show1() throws IOException {
    //bufferedWriter
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("a.txt"));
        for (int i = 0; i < 5; i++) {
            bufferedWriter.write("niho");
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    private static void show() throws IOException {
        //bufferedoutputStream
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("a.txt"));
        bufferedOutputStream.write("nihoa".getBytes());
        bufferedOutputStream.close();
    }
}
