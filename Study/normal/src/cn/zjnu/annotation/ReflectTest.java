package cn.zjnu.annotation;

import cn.zjnu.A;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
@Pro(className = "cn.zjnu.A",methodName = "add")
public class ReflectTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class rflectTestClass = ReflectTest.class;
        Pro pro = (Pro) rflectTestClass.getAnnotation(Pro.class);

        String className = pro.className();
        String methodName = pro.methodName();
        Class AClass = Class.forName(className);
        Constructor constructor = AClass.getConstructor(int.class);
        Object a = constructor.newInstance(9);
        System.out.println(a);

        Method mAdd = AClass.getMethod("add",int.class,int.class);
        A a1= new A();
        System.out.println(mAdd.invoke(a1,1,2));

    }
}
