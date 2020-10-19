package cn.zjnu.counter;

import cn.zjnu.reflect.ReflectTest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;
public  class Counter  {
    private static HashMap<String,Integer> field = new HashMap<>();
    private static HashMap<String,Integer> constructor = new HashMap<>();
    private static HashMap<String,Integer> method = new HashMap<>();
    private static HashMap<String,Integer> others = new HashMap<>();
    private static String [] keyWords={"abstract","double","int","super","assert","else","interface"
            ,"switch","boolean","enum","long","synchronized","break","extends","native"
            ,"this","byte","final","new","throw","case","finally","package","throws","catch"
            ,"float","private","transient","char","for","protected","try","class","goto","public"
            ,"void","const","if","return","volatile","continue","implements","short","while"
            ,"default","import","static","do","instanceof","strictfp","String","Integer"
    };
    private static String[] emptyWords ={";","{","(","'",".","\""};
    private static boolean isField =false;
    private static boolean isContrucor =false;
    private static boolean isMethod =false;
    private static boolean isClass =false;
    private static boolean isNote =false;
    private static HashMap<String,Integer> variable = new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        initial();
        counter();
        showResult();
    }
    private static void initial() {
            others.put("import", 0);
            others.put("keywords", 0);
            constructor.put("keywords",0);
            constructor.put("emptystatment", 0);
            method.put("keywords", 0);
            method.put("emptystatment", 0);
            field.put("keywords", 0);
            method.put("variables",0);
    }
    private static void counter() throws ClassNotFoundException,IOException {
        //统计size
        Properties pro= new Properties();
        InputStream ins = ReflectTest.class.getClassLoader().getResourceAsStream("pro.properties");
        pro.load(ins);
        String className = pro.getProperty("className");
        Class CounteredClass = Class.forName(className);
        Field [] fields = CounteredClass.getDeclaredFields();
        field.put("fieldNum",fields.length);
        Method [] methods = CounteredClass.getDeclaredMethods();
        method.put("methodNum",methods.length);
        Constructor [] constructors = CounteredClass.getDeclaredConstructors();
        constructor.put("constructorNum",constructors.length);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\IDEA\\workspace\\Study\\normal\\src\\cn\\zjnu\\A.java"));
        String buffer;
        int i = 0;
        while((buffer=bufferedReader.readLine())!=null){
           if(!buffer.isEmpty()){
               if(buffer.trim().matches("//class")){
                   isClass = true;
               }
               if (buffer.trim().matches("//fields")){
                   isField =true;
               }else if (buffer.trim().matches("//constructors")){
                   isField = false;
                   isContrucor = true;
               }else if (buffer.trim().matches("//methods")){
                   isContrucor = false;
                   isMethod = true;
               }
               buffer= buffer.replaceAll("//[.]*","");
               if(buffer.trim().matches("/*")){
                   isNote= true;
               }
               if(buffer.trim().matches("\\*/")){
                   isNote =false;
               }
               if(!isNote) {
                   if (!isClass) {
                       others.put("import", others.get("import") + containSub(buffer, "import"));
                       others.put("keywords", others.get("keywords") + counterKeywords(buffer));
                   } else if (isField) {
                       field.put("keywords", field.get("keywords") + counterKeywords(buffer));
                   } else if (isContrucor) {
                       constructor.put("keywords",constructor.get("keywords") + counterKeywords(buffer));
                       constructor.put("emptystatment", constructor.get("emptystatment") + counterEmpty(buffer));
                   } else if (isMethod) {
                       method.put("keywords", method.get("keywords") + counterKeywords(buffer));
                       method.put("emptystatment", method.get("emptystatment") + counterEmpty(buffer));
                       method.put("variables",counterVariables(buffer));
                   }
               }
           }
        }
        bufferedReader.close();
    }
    private static void showResult() {
        //展示结果
        int size=0;
        int num = 0;
        System.out.println("成员变量： ");
        int temp= 0;
        for(String s:field.keySet()){
            num =field.get(s);
            temp+=num;
            System.out.println(s+" : "+num);
            size+=num;
        }
        System.out.println("共："+temp);
        temp = 0;
        System.out.println("----------------------");
        System.out.println("构造函数： ");
        for(String s:constructor.keySet()){
            num =constructor.get(s);
            temp+=num;
            System.out.println(s+" : "+num);
            size+=num;
        }
        System.out.println("共："+temp);
        temp = 0;
        System.out.println("----------------------");
        System.out.println("方法： ");
        for(String s:method.keySet()){
            num =method.get(s);
            temp+=num;
            System.out.println(s+" : "+num);
            size+=num;
        }
        System.out.println("共："+temp);
        temp = 0;
        System.out.println("----------------------");
        System.out.println("其他： ");
        for(String s:others.keySet()){
            num =others.get(s);
            temp+=num;
            System.out.println(s+" : "+num);
            size+=num;
        }
        System.out.println("共："+temp);
        temp = 0;
        System.out.println("----------------------");
        System.out.println("total size: "+size);
    }
    public static Integer counterVariables(String buffer) {
        //统计变量
        int indexOf_ = buffer.indexOf("_");
        if(indexOf_!=-1){
            buffer = buffer.replaceAll("[/&!~?.';+=(),]"," ");
            int indexOfSpace =buffer.indexOf(" ",indexOf_);
            if(indexOfSpace!=-1){
                variable.put(buffer.substring(indexOf_+1,indexOfSpace),1);
            }
            while((indexOf_=buffer.indexOf("_",indexOf_+1))!=-1){
                indexOfSpace =buffer.indexOf(" ",indexOf_+1);
                if(indexOfSpace!=-1){
                    variable.put(buffer.substring(indexOf_+1,indexOfSpace),1);
                }
            }
        }
        return variable.size();
    }
    private static Integer counterEmpty(String buffer) {
        //统计空字符
        int num=0;
        int numOf1 = 0;
        int numOf2 = 0;
        for(String s:emptyWords){
            if(s.equals("\"")){
                numOf1 =containSub(buffer,s);
            }else if(s.equals("'")){
                numOf2 =containSub(buffer,s);
            }else {
                num+=containSub(buffer,s);
            }
        }
        num+=numOf1/2+numOf1%2+numOf2/2+numOf2%2;
        return num;
    }
    private static Integer counterKeywords(String buffer) {
        //统计关键字
        int num=0;
        for(String s:keyWords){
            num+=containSub(buffer,s);
        }
        return num;
    }
    public static int containSub(String source, String sub) {
        //计算返回字符串中包含某个子字符串的个数
        int i = source.length() - source.replace(sub, "").length();
        return  i/sub.length();
    }
}