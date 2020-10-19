package cn.zjnu.file;

import java.io.File;
import java.io.IOException;

public class TestFile {
    public static void main(String[] args) throws IOException {

        show2();
    }

    private static void show2() {

    }

    private static void show1() {
        //遍历文件
        File file = new File("D:\\IDEA\\workspace\\Study\\normal");
        getAllFiles(file);
    }

    private static void getAllFiles(File file) {
       File [] fileList = file.listFiles(new FileFilterImpl());
       for(File f: fileList){
            if(f.isDirectory()){
                getAllFiles(f);
            }else{
                System.out.println(f.getName());
            }
       }
    }

    private static void show() {
        System.out.println(File.pathSeparator);
        System.out.println(File.pathSeparatorChar);
        System.out.println(File.separator);
        System.out.println(File.separatorChar);
        File file = new File("D:\\software\\a.txt");
        System.out.println(file.delete());
    }

}
