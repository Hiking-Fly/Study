package cn.zjnu.reflect;

import cn.zjnu.A;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Testreflect {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        //Class cla = Class.forName("cn.zjnu.A");

       // Class cla= a.getClass();
        //System.out.println(cla);
        show3();
    }

    private static void show3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class AClass = A.class;
        Method mAdd = AClass.getMethod("add",int.class,int.class);
        A a1= new A();
        System.out.println(mAdd.invoke(a1,1,2));
        System.out.println(mAdd.getName());
    }

    private static void show2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class AClass = A.class;
        Constructor constructor = AClass.getConstructor(int.class);
        System.out.println(constructor);
        Object a = constructor.newInstance(9);
        System.out.println(a);

        Object a1= AClass.newInstance();
        System.out.println(a1);

    }

    private static void show1() throws NoSuchFieldException, IllegalAccessException {
        Class AClass = A.class;
        Field [] AFields = AClass.getDeclaredFields();
        for(Field f:AFields){
            System.out.println(f);
        }
        Field AField = AClass.getDeclaredField("b");
        A a1 = new A();
        AField.setAccessible(true);
        AField.set(a1,'b');//暴力反射
        System.out.println(AField.get(a1));

    }

    private static void show() throws NoSuchFieldException, IllegalAccessException {
        Class AClass = A.class;
        Field AField = AClass.getField("a");
        A a1 = new A();
        a1.setA(3);
        A a2 = new A();
        a2.setA(2);
        System.out.println(AField.get(a1));
        AField.set(a1,4);
        System.out.println(AField.get(a1));
        System.out.println(AField.get(a2));
    }
}
