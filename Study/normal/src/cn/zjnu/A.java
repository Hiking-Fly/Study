package cn.zjnu;
import java.util.Objects;
//class
public class A {
    /*
     注释注释！！
     */
    //fields
   public static int a=1;
   private char b= 'a';
   String c;
   protected boolean d;
    //constructors
    public A() {
        this.a=2;
    }
    public A(int a) {
        this.a = a;
    }
    //methods
    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        A a1 = (A) _o;
        return a == a1.a &&
                b == a1.b &&
                d == a1.d &&
                Objects.equals(c, a1.c);
    }
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }
    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b=" + b +
                ", c='" + c + '\'' +
                ", d=" + d +
                '}';
    }
    public int add(int _num1,int _num2){
        return _num1+_num2;
    }
}
