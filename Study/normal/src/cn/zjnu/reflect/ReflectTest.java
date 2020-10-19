package cn.zjnu.reflect;

import cn.zjnu.A;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Properties pro= new Properties();
        InputStream ins = ReflectTest.class.getClassLoader().getResourceAsStream("pro.properties");
        pro.load(ins);
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");
        Class AClass = Class.forName(className);
        Constructor constructor = AClass.getConstructor(int.class);
        Object a = constructor.newInstance(9);
        System.out.println(a);

        Method mAdd = AClass.getMethod("add",int.class,int.class);
        A a1= new A();
        System.out.println(mAdd.invoke(a1,1,2));
        
    }
}
