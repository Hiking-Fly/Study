package cn.zjnu.io;

import java.io.*;
import java.util.Properties;

public class io {
    public static void main(String[] args) throws IOException {
       
        show8();

    }

    private static void show8() throws IOException {
        //Properties -》HashTable -> Map
        Properties properties = new Properties();
        properties.setProperty("刘德华","185");
        properties.setProperty("周坤","185");
        properties.setProperty("小李子","185");
        FileWriter fileWriter = new FileWriter("D:\\a.txt",true);
        properties.store(fileWriter,"save");
        fileWriter.close();
        System.out.println(properties);
    }

    private static void show7() {
        //文件处理，异常处理
        try(FileInputStream fileInputStream = new FileInputStream("D:\\a.txt");) {
            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                //System.out.println(Arrays.toString(bytes));
                System.out.print(new String(bytes, 0, len));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void show6() throws IOException {
        //和其他的不同的是，这个类先写到内存中，flush后再写到外存
        //因为需要进行字节和字符的转换，一般close() 或 flush()会把内存中的数据刷新到外存
        FileWriter fileWriter = new FileWriter("D:\\a.txt",true);
        fileWriter.write("wheinanvaprupgahlnzuvbrgbaeigpibwribiafv");

        fileWriter.close();
    }

    private static void show5() throws IOException {
        FileReader fileReader = new FileReader("D:\\a.txt");
        char [] chars = new char[256];
        int len = 0;
       /* while((len=fileReader.read())!=-1){
            System.out.println(len);
        }*/

        while(fileReader.read(chars)!=-1){
            System.out.print(new String(chars,0,chars.length));
        }
        fileReader.close();
    }

    private static void show4() throws IOException {
        //复制文件
        FileInputStream fileInputStream = new FileInputStream("D:\\a.txt");
        int len;
        byte [] bytes = new byte[1024];
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\software\\a.txt");
        while((len = fileInputStream.read(bytes))!=-1){
            fileOutputStream.write(bytes,0,len);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    private static void show3() throws IOException {
        //缓冲读
        FileInputStream fileInputStream = new FileInputStream("D:\\a.txt");
        int len;
        byte [] bytes = new byte[1024];
        while((len = fileInputStream.read(bytes))!=-1){
            //System.out.println(Arrays.toString(bytes));
            System.out.print(new String(bytes,0,len));
        }

        fileInputStream.close();
    }

    private static void show2() throws IOException {
        //读
        FileInputStream fileInputStream = new FileInputStream("D:\\a.txt");
        int len;
        while((len = fileInputStream.read())!=-1){
            System.out.println(len);
        }

        fileInputStream.close();
    }

    private static void show1() throws IOException {
        //附加写
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\a.txt",true);
        byte [] bytes = "你好呀".getBytes();
        for (int i = 0; i < 5; i++) {
            fileOutputStream.write(bytes);
            fileOutputStream.write("\n".getBytes());

        }

        fileOutputStream.close();
    }
}
