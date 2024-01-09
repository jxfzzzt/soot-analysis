package edu.fdu.se;

public class PlainTest {
    public static void main(String[] argv) {
        int x = 2, y = 6;

        System.out.println("Hi!");
        System.out.println(x * y + y);
        try {
            int z = y * x;
            System.out.println(z);
        } catch (Exception e) {
            throw e;
        }
    }
}
