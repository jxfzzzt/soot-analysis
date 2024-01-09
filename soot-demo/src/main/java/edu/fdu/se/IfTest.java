package edu.fdu.se;

import java.util.Scanner;

public class IfTest {

    public int get() {
        int x = 2;
        return x;
    }

    public void test(int y) {

        int x = get();
        if(x < 5) {
            System.out.println("less than five");
        } else {
            System.out.println("greater or equal than five");
        }
    }
}
