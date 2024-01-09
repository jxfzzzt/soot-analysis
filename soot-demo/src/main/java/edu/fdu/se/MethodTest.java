package edu.fdu.se;

import edu.fdu.se.domain.A;

import java.util.ArrayList;
import java.util.List;

public class MethodTest {

    private int number;

    private A a;

    public List<String> getListString() {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        return list;
    }


    public List<String> test01(int x, int y) {
        if (add(x, y) >= 5) {
            return getListString();
        } else {
            return null;
        }
    }

    public void test02() {
        A a = new A();
        int x = a.getAge();
        x += number;
        System.out.println(x);
    }

    public void test03(int x) {
        A a = new A();
        System.out.println(a);
    }

    public void test04() {
        int age = this.a.getAge();
        System.out.println(age);
    }

    public void test05() {
        this.a = new A();
        this.number = 5;
        System.out.println(this.number);
        A aa = new A();
        aa.age = a.age;
        aa.setName(a.getName());
    }


    private int add(int a, int b) {
        int sum = a + b;
        return sum;
    }

    public void test06(int x) {
        int add = add(this.number, x);
        System.out.println(add);
    }

}
